package lk.ijse.greenfiber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
    private String productCode;
    private String description;
    private Integer qtyOnHand;
    private Double cost;
    private Double unitPrice;

    public ProductDTO(String description, Double cost) {
        this.description=description;
        this.cost=cost;
    }
}
