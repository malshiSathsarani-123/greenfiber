package lk.ijse.greenfiber.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ManufacturingCostTm {
    private String mattirialCode;
    private Integer qty;
    private Double unitPrice;
    private Double total;
}
