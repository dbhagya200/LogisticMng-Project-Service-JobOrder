package lk.ijse.eca.orderservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lk.ijse.eca.orderservice.dto.JobOrderDto;
import lk.ijse.eca.orderservice.service.JobOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job-orders")
@RequiredArgsConstructor
@Slf4j
@Validated
public class JobOrderController {

    private final JobOrderService orderService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<JobOrderDto> createJobOrder(
            @Valid @RequestBody JobOrderDto dto) {
        log.info("POST /api/v1/job-orders - customerNic: {}", dto.getCustomerNic());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createJobOrder(dto));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobOrderDto> getJobOrder(
            @PathVariable @NotBlank(message = "JobOrder ID must not be blank") String id) {
        log.info("GET /api/v1/job-orders/{}", id);
        return ResponseEntity.ok(orderService.getJobOrder(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<JobOrderDto>> getAllJobOrders() {
        log.info("GET /api/v1/job-orders - retrieving all jobOrders");
        return ResponseEntity.ok(orderService.getAllJobOrders());
    }

    @GetMapping(params = "customerNic", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<JobOrderDto>> getJobOrdersByCustomerNic(
            @RequestParam @NotBlank(message = "customer Nic must not be blank") String customerNic) {
        log.info("GET /api/v1/job-orders?customerNic={} - retrieving jobOrders by customer", customerNic);
        return ResponseEntity.ok(orderService.getJobOrderByCustomerNic(customerNic));
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<JobOrderDto> updateJobOrder(
            @PathVariable @NotBlank(message = "JobOrder ID must not be blank") String id,
            @Valid @RequestBody JobOrderDto dto) {
        log.info("PUT /api/v1/job-orders/{}", id);
        return ResponseEntity.ok(orderService.updateJobOrder(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobOrder(
            @PathVariable @NotBlank(message = "JobOrder ID must not be blank") String id) {
        log.info("DELETE /api/v1/job-orders/{}", id);
        orderService.deleteJobOrder(id);
        return ResponseEntity.noContent().build();
    }

}
