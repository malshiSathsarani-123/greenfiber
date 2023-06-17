package lk.ijse.greenfiber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuppliesDetailDTO {
    private String suppliesId;
    private String materialCode;
    private Integer materialQty;
    private Double materialCost;
    private Double Total;
}
