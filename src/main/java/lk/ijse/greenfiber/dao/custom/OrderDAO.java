package lk.ijse.greenfiber.dao.custom;

import lk.ijse.greenfiber.dao.SuperDAO;
import lk.ijse.greenfiber.entity.Orders;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface OrderDAO extends SuperDAO {
    public String getNextId() throws SQLException ;

    public boolean save(Orders orders) throws SQLException ;

    public String getOrderId() throws SQLException ;

    public boolean update(Orders orders) throws SQLException ;

    public List<String> genarateId() throws SQLException ;

    public List<String> genarateIdNotDelivery() throws SQLException ;

    public Integer getCount() throws SQLException ;

    public Double getAmount(String id) throws SQLException ;

    public String getCustomerId(String id) throws SQLException ;

    public boolean updatePayment(String orderId, Double amount) throws SQLException ;
}
