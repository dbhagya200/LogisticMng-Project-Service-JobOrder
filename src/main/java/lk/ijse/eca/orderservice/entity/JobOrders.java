package lk.ijse.eca.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document(collection = "job_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobOrders {

    @Id
    private String id;

    @Field("customer_nic")
    private String customerNic;

    @Field("pickup_location")
    private String pickupLocation;

    @Field("delivery_location")
    private String deliveryLocation;

    @Field("created_at")
    private LocalDate createdAt;
}
