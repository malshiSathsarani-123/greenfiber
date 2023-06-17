package lk.ijse.greenfiber.bo.custom;

import lk.ijse.greenfiber.bo.SuperBO;
import lk.ijse.greenfiber.dto.UserDTO;

import java.sql.SQLException;

public interface UserBO extends SuperBO {

    boolean delete(String id) throws SQLException;

    boolean save(UserDTO dto) throws SQLException;

    boolean update(UserDTO dto) throws SQLException;
}
