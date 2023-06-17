package lk.ijse.greenfiber.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {
    private String name;
    private String contact;
    private String password;
    private String mail;
}
