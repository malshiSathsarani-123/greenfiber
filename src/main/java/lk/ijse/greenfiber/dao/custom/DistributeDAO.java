package lk.ijse.greenfiber.dao.custom;

import lk.ijse.greenfiber.dao.SuperDAO;

import java.sql.SQLException;

public interface DistributeDAO extends SuperDAO {
    public String getNextDeliveryId() throws SQLException;
}
