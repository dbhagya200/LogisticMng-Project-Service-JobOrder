package lk.ijse.eca.orderservice.service;

import lk.ijse.eca.orderservice.dto.JobOrderDto;

import java.util.List;

public interface JobOrderService {

    JobOrderDto createJobOrder(JobOrderDto dto);

    JobOrderDto updateJobOrder(String id, JobOrderDto dto);

    void deleteJobOrder(String id);

    JobOrderDto getJobOrder(String id);

    List<JobOrderDto> getAllJobOrders();

    List<JobOrderDto> getJobOrderByCustomerNic(String customerNic);
}
