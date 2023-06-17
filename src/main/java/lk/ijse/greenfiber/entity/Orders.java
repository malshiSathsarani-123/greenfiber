package lk.ijse.greenfiber.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Orders {
    private String orderId;
    private String amount;
    private String date;
    private String time;
    private String customerId;
    private String deliveryId;
    private String employeeId;
    private String vehicleId;
    private String OutstandingAmount;

    public Orders(String orderId, String amount, String date, String time, String customerId) {
        this.orderId = orderId;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.customerId = customerId;
    }

    public Orders(String deliveryId, String employeeId, String vehicleId,String orderId) {
        this.deliveryId = deliveryId;
        this.employeeId = employeeId;
        this.vehicleId = vehicleId;
        this.orderId = orderId;
    }
}
