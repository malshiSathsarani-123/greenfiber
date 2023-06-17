package lk.ijse.greenfiber.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PaymentTM {
    private String payId;
    private String date;
    private Double amount;
    private Double balance;
    private String type;
    private String orderId;
    private String suppliesId;
}
