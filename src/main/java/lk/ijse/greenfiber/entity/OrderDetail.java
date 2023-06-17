package lk.ijse.greenfiber.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetail {
    private String orderId;
    private String productCode;
    private Integer productQty;
    private Double unitPrice;
    private Double total;
}
