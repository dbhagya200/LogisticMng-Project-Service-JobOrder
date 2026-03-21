package lk.ijse.eca.orderservice.client;

import lk.ijse.eca.orderservice.dto.CustomerDto;
import lk.ijse.eca.orderservice.exception.CustomerServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
@Slf4j
public class CustomerServiceClient {

    private final RestClient restClient;

    public CustomerServiceClient(@LoadBalanced RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("http://CUSTOMER-SERVICE")
                .build();
    }

    public CustomerDto getCustomer(String customerNic) {
        log.debug("Fetching customer from Customer-Service for NIC: {}", customerNic);
        try {
            return restClient.get()
                    .uri("/api/v1/customers/{nic}", customerNic)
                    .retrieve()
                    .body(CustomerDto.class);
        } catch (RestClientException e) {
            log.error("Failed to fetch customer details for NIC: {}", customerNic, e);
            throw new CustomerServiceException(
                    "Unable to retrieve customer details for: " + customerNic, e);
        }
    }
}
