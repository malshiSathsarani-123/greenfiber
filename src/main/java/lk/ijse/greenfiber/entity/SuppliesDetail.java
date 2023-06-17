package lk.ijse.greenfiber.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuppliesDetail {
    private String productCode;
    private String materialCode;
    private Integer materialQty;
    private Double materialCost;
    private Double Total;
}
