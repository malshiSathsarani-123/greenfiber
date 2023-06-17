package lk.ijse.greenfiber.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ManufacturingTM {
    private String MattirialCode;
    private Integer Qty;
    private Integer TotalQty;
}
