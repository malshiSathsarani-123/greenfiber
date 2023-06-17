package lk.ijse.greenfiber.tm;

import javafx.scene.control.Button;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceSuppliesTM {
    private String code;
    private String description;
    private Integer qty;
    private Double unitPrice;
    private Double total;
    private Button btn;
}
