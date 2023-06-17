package lk.ijse.greenfiber.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SupplierDTO {
    private String Supplier_Id;
    private String First_Name;
    private String Last_Name;
    private String Company_Name;
    private String Nic;
    private Integer Contact;
}
