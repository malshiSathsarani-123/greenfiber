package lk.ijse.greenfiber.dao.custom.impl;

import lk.ijse.greenfiber.dao.custom.OrderDAO;
import lk.ijse.greenfiber.entity.Orders;
import lk.ijse.greenfiber.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public String getNextId() throws SQLException {
        String sql = "SELECT Order_id FROM orders ORDER BY Order_id DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()){
            return nextId(resultSet.getString(1));
        }
        return nextId(null);
    }


    private String nextId(String currentId) {
        if (currentId != null){
            String[] strings = currentId.split("O0");
            int id = Integer.parseInt(strings[1]);
            id++;

            return "O0"+id;
        }
        return "O001";
    }
    @Override
    public boolean save(Orders orders) throws SQLException {
        String sql = "INSERT INTO orders (Order_Id,Amount,Date,Time,Customer_Id,TheOutstandingAmount) VALUES (?,?,?,?,?,?)";
        return CrudUtil.execute(sql,orders.getOrderId(),orders.getAmount(),orders.getDate(),orders.getTime(),orders.getCustomerId(),orders.getAmount());
    }


    @Override
    public String getOrderId() throws SQLException {
        String sql = "SELECT Order_id FROM orders ORDER BY Order_id DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()){
            String id = resultSet.getString(1);
            return id;
        }
        return "O001";
    }

    @Override
    public boolean update(Orders orders) throws SQLException {
        String sql ="UPDATE orders SET deliveryId = ?, Employee_Id = ?, Vehicle_Id = ? WHERE Order_id = ?";
        return CrudUtil.execute(sql,orders.getDeliveryId(),orders.getEmployeeId(),orders.getVehicleId(),orders.getOrderId());
    }

    @Override
    public List<String> genarateId() throws SQLException {
        String sql = "SELECT Order_id FROM orders ORDER BY Order_id ASC";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String>id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }
        return id;
    }

    @Override
    public List<String> genarateIdNotDelivery() throws SQLException {
        String sql = "SELECT Order_id FROM orders WHERE deliveryId IS NULL ORDER BY Order_id ASC";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String>id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }
        return id;
    }

    @Override
    public Integer getCount() throws SQLException {
        String sql = "SELECT COUNT(Order_id) FROM orders";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;    }

    @Override
    public Double getAmount(String id) throws SQLException {
        String sql = "SELECT TheOutstandingAmount FROM orders WHERE Order_id = ?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        if (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return 0.0;
    }

    @Override
    public String getCustomerId(String id) throws SQLException {
        String sql = "SELECT Customer_Id FROM orders WHERE Order_id = ?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean updatePayment(String orderId, Double amount) throws SQLException {
        String sql = "UPDATE orders SET TheOutstandingAmount = (TheOutstandingAmount - ?) WHERE Order_id = ?";
        return CrudUtil.execute(sql,amount,orderId);
    }
}
