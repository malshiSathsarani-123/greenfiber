package lk.ijse.greenfiber.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductionDetail {
    private String product_Code;
    private String material_Code;
    private Integer material_Qty;
    private Double material_Cost;

    public ProductionDetail(String materialCode, Integer qty) {
        this.material_Code =materialCode;
        this.material_Qty=qty;
    }
}
