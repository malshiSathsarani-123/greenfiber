package lk.ijse.greenfiber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleDTO {
    private String id;
    private String no;
    private String type;
    private String color;
    private String name;
}
