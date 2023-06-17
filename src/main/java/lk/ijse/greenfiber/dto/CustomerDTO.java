package lk.ijse.greenfiber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private String customer_Id;
    private String first_Name;
    private String last_Name;
    private String address;
    private String nic;
    private Integer contact;
    private String gender;
}
