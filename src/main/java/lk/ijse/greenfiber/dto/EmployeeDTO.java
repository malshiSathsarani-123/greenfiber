package lk.ijse.greenfiber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {
    private String employee_Id;
    private String first_Name;
    private String last_Name;
    private String address;
    private String nic;
    private Integer contact;
    private String role;
    private String gender;
}
