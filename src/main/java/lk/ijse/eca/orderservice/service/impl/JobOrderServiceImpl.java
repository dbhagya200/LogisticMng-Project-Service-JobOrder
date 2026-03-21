package lk.ijse.eca.orderservice.service.impl;

import lk.ijse.eca.orderservice.client.CustomerServiceClient;
import lk.ijse.eca.orderservice.dto.CustomerDto;
import lk.ijse.eca.orderservice.dto.JobOrderDto;
import lk.ijse.eca.orderservice.entity.JobOrders;
import lk.ijse.eca.orderservice.exception.JobOrderNotFoundException;
import lk.ijse.eca.orderservice.mapper.JobOrdersMapper;
import lk.ijse.eca.orderservice.repository.JobOrderRepository;
import lk.ijse.eca.orderservice.service.JobOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobOrderServiceImpl implements JobOrderService {

    private final JobOrderRepository jobOrderRepository;
    private final JobOrdersMapper jobOrdersMapper;
    private final CustomerServiceClient customerServiceClient;

    @Override
    public JobOrderDto createJobOrder(JobOrderDto dto) {
        log.debug("Creating jobOrder for customer: {}", dto.getCustomerNic());

        CustomerDto customer = customerServiceClient.getCustomer(dto.getCustomerNic());

        JobOrders jobOrders = jobOrdersMapper.toEntity(dto);
        JobOrders saved = jobOrderRepository.save(jobOrders);
        log.info("JobOrder created successfully with ID: {}", saved.getId());
        return jobOrdersMapper.toDto(saved, customer);
    }

    @Override
    public JobOrderDto updateJobOrder(String id, JobOrderDto dto) {
        log.debug("Updating jobOrder with ID: {}", id);

        JobOrders jobOrders = jobOrderRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("JobOrder not found for update: {}", id);
                    return new JobOrderNotFoundException(id);
                });

        CustomerDto customer = customerServiceClient.getCustomer(dto.getCustomerNic());

        jobOrdersMapper.updateEntity(dto, jobOrders);
        JobOrders updated = jobOrderRepository.save(jobOrders);
        log.info("JobOrder updated successfully: {}", id);
        return jobOrdersMapper.toDto(updated, customer);
    }


    @Override
    public void deleteJobOrder(String id) {
        log.debug("Deleting JobOrder with ID: {}", id);

        JobOrders jobOrders = jobOrderRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("JobOrder not found for deletion: {}", id);
                    return new JobOrderNotFoundException(id);
                });

        jobOrderRepository.delete(jobOrders);
        log.info("JobOrder deleted successfully: {}", id);
    }

    @Override
    public JobOrderDto getJobOrder(String id) {
        log.debug("Fetching JobOrder with ID: {}", id);

        JobOrders jobOrders = jobOrderRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("JobOrder not found: {}", id);
                    return new JobOrderNotFoundException(id);
                });

        CustomerDto customer = customerServiceClient.getCustomer(jobOrders.getCustomerNic());
        return jobOrdersMapper.toDto(jobOrders, customer);
    }

    @Override
    public List<JobOrderDto> getAllJobOrders() {
        log.debug("Fetching all jobOrders");

        List<JobOrderDto> jobOrders = jobOrderRepository.findAll()
                .stream()
                .map(jobOrder -> {
                    CustomerDto customer = customerServiceClient.getCustomer(jobOrder.getCustomerNic());
                    return jobOrdersMapper.toDto(jobOrder, customer);
                })
                .toList();

        log.debug("Fetched {} jobOrder(s)", jobOrders.size());
        return jobOrders;
    }

    @Override
    public List<JobOrderDto> getJobOrderByCustomerNic(String customerNic) {
        log.debug("Fetching jobOrders for customerNic: {}", customerNic);

        List<JobOrderDto> jobOrders = jobOrderRepository.findByCustomerNicOrderByCreatedAtDescIdDesc(customerNic)
                .stream()
                .map(jobOrder -> {
                    CustomerDto customer = customerServiceClient.getCustomer(jobOrder.getCustomerNic());
                    return jobOrdersMapper.toDto(jobOrder, customer);
                })
                .toList();

        log.debug("Fetched {} jobOrder(s) for customerNic: {}", jobOrders.size(), customerNic);
        return jobOrders;
    }
}
