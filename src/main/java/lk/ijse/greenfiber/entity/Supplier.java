package lk.ijse.greenfiber.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Supplier {
    private String Supplier_Id;
    private String First_Name;
    private String Last_Name;
    private String Company_Name;
    private String Nic;
    private Integer Contact;
}
