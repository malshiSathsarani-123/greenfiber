package lk.ijse.greenfiber.dao.custom;

import lk.ijse.greenfiber.dao.SuperDAO;
import lk.ijse.greenfiber.entity.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO extends SuperDAO {

    public boolean save(String orderId, List<OrderDetail> orderDtoList) throws SQLException ;

    }
