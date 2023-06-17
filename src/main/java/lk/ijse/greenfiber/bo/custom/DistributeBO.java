package lk.ijse.greenfiber.bo.custom;

import lk.ijse.greenfiber.bo.SuperBO;
import lk.ijse.greenfiber.dto.OrdersDTO;

import java.sql.SQLException;
import java.util.List;

public interface DistributeBO extends SuperBO {

    List<String> genarateOrderId() throws SQLException;

    List<String> genarateDriversId() throws SQLException;

    List<String> genarateVehicleId() throws SQLException;

    String getNextDeliveryId() throws SQLException;

    boolean updateOrder(OrdersDTO dto) throws SQLException;

    String getDriverName(String DriverId) throws SQLException;

    String getVehicleType(String selectedItem) throws SQLException;

    String getOrderId() throws SQLException;
}
