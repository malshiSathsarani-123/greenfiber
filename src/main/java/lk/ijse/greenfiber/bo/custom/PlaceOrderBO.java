package lk.ijse.greenfiber.bo.custom;

import lk.ijse.greenfiber.bo.SuperBO;
import lk.ijse.greenfiber.dto.OrderDetailDTO;
import lk.ijse.greenfiber.dto.OrdersDTO;
import lk.ijse.greenfiber.dto.ProductDTO;

import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {

    List<String> genarateProductCode() throws SQLException;

    List<String> genarateCustomerId() throws SQLException;

    String getNextOrderId() throws SQLException;

    String getCustomerName(String id) throws SQLException;

    ProductDTO searchProduct(String code) throws SQLException;

    boolean saveOrder(OrdersDTO ordersDTO, List<OrderDetailDTO> orderDtoList) throws SQLException;
}
