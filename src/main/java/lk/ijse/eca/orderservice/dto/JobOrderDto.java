package lk.ijse.eca.orderservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobOrderDto {

    private String id;

    @NotNull(message = "Customer NIC is required")
    private String customerNic;

    private CustomerDto customer;

    @NotNull(message = "Pickup location is required")
    private String pickupLocation;

    @NotNull(message = "Delivery location is required")
    private String deliveryLocation;

    @NotNull(message = "Date is required")
    private LocalDate createdAt;

}
