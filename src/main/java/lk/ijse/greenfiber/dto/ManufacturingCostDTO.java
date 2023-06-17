package lk.ijse.greenfiber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ManufacturingCostDTO {
    private String productCode;
    private String materialCode;
    private Integer qty;
    private Double cost;
}
