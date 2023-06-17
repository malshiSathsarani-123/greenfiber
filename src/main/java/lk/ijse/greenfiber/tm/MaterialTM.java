package lk.ijse.greenfiber.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class MaterialTM {
    private String mattirial_Id;
    private String description;
    private Integer qty_on_hand;
    private Double unit_price;
}
