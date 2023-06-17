package lk.ijse.greenfiber.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vehicle {
    private String id;
    private String no;
    private String type;
    private String color;
    private String name;
}
