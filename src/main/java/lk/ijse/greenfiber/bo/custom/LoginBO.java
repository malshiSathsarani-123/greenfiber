package lk.ijse.greenfiber.bo.custom;

import lk.ijse.greenfiber.bo.SuperBO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {

    boolean conform(String name, String password) throws SQLException;
}
