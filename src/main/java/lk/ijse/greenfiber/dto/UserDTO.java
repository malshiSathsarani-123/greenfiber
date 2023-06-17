package lk.ijse.greenfiber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDTO {
    private String name;
    private String contact;
    private String password;
    private String mail;
}
