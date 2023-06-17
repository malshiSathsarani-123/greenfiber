package lk.ijse.greenfiber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductionDetailDTO {
    private String materialCode;
    private Integer qty;
    private Double total;

    public ProductionDetailDTO(String materialCode, Integer qty) {
        this.materialCode =materialCode;
        this.qty=qty;
    }
}
