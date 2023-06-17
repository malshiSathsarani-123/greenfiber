package lk.ijse.greenfiber.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Material {

    private String mattirial_Id;
    private String description;
    private Integer qty_on_hand;
    private Double unit_price;
}
