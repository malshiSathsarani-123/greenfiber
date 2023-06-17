package lk.ijse.greenfiber.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private String productCode;
    private String description;
    private Integer qtyOnHand;
    private Double cost;
    private Double unitPrice;

    public Product(String description, Double cost) {
        this.description=description;
        this.cost=cost;
    }
}
