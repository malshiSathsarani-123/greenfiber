package lk.ijse.greenfiber.bo.custom;

import lk.ijse.greenfiber.bo.SuperBO;

import java.sql.SQLException;

public interface DashboardBO extends SuperBO {

    Double getBalance() throws SQLException;

    Integer getAttendenceCount() throws SQLException;

    Integer getAbsentCount() throws SQLException;

    Integer getSuppliesCount() throws SQLException;

    Integer getSupplierCount()throws SQLException;

    Integer getOrderCount()throws SQLException;

    Integer getCustomerCount()throws SQLException;
}
