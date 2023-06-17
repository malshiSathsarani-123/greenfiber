package lk.ijse.greenfiber.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;

@AllArgsConstructor
@Data
public class Payment {
    private String payId;
    private String date;
    private Time time;
    private Double amount;
    private Double balance;
    private String orderId;
    private String suppliesId;
    private String type;
}
