package lk.ijse.greenfiber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetailDTO {
    private String orderId;
    private String code;
    private Integer qty;
    private Double unitPrice;
    private Double total;
}
