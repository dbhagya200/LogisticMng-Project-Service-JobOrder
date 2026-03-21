package lk.ijse.eca.orderservice.repository;

import lk.ijse.eca.orderservice.entity.JobOrders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOrderRepository extends MongoRepository<JobOrders, String> {
    List<JobOrders> findByCustomerNicOrderByCreatedAtDescIdDesc(String customerNic);
}
