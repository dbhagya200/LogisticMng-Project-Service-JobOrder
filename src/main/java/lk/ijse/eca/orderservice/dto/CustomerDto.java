package lk.ijse.eca.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    @JsonIgnore
    private String nic;
    private String name;
    private String address;
    private String postalCode;
    private String mobile;
    private String email;
    private String picture;
}
