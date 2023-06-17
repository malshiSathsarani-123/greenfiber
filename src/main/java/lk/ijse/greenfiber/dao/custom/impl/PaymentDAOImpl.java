package lk.ijse.greenfiber.dao.custom.impl;

import lk.ijse.greenfiber.dao.custom.PaymentDAO;
import lk.ijse.greenfiber.entity.Payment;
import lk.ijse.greenfiber.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean saveOrder(Payment payment) throws SQLException {
        String sql = "INSERT INTO payment (Payment_Id,Date,Time,Amount,Balance,Payment_Type,Order_id) VALUES (?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,payment.getPayId(),payment.getDate(),payment.getTime(),payment.getAmount(),payment.getBalance(),payment.getType(),payment.getOrderId());
    }

    @Override
    public boolean saveSupplies(Payment payment) throws SQLException {
        String sql = "INSERT INTO payment (Payment_Id,Date,Time,Amount,Balance,Payment_Type,Supplies_id) VALUES (?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,payment.getPayId(),payment.getDate(),payment.getTime(),payment.getAmount(),payment.getBalance(),payment.getType(),payment.getSuppliesId());
    }
    @Override
    public Double getBalance() throws SQLException {
        String sql = "SELECT Balance FROM payment ORDER BY Payment_Id DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return 0.0;
    }

    @Override
    public String getNextId() throws SQLException {
        String sql = "SELECT Payment_Id FROM payment ORDER BY Payment_Id DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()){
            return nextId(resultSet.getString(1));
        }
        return nextId(null);
    }

    private static String nextId(String currentId) {
        if (currentId != null){
            String[] strings = currentId.split("P0");
            int id = Integer.parseInt(strings[1]);
            id++;

            return "P0"+id;
        }
        return "P001";
    }

    @Override
    public List<Payment> getAll() throws SQLException {
        String sql = "SELECT * FROM payment";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<Payment> paymentList = new ArrayList<>();

        while (resultSet.next()){
            paymentList.add(new Payment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getTime(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5),
                    resultSet.getString(8),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return paymentList;
    }
}
