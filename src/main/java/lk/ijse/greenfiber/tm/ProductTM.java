package lk.ijse.greenfiber.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductTM {
    private String productCode;
    private String description;
    private Integer qtyOnHand;
    private Double cost;
    private Double unitPrice;
}
