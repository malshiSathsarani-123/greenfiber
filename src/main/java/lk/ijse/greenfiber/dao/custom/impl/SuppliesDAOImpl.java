package lk.ijse.greenfiber.dao.custom.impl;

import lk.ijse.greenfiber.dao.custom.SuppliesDAO;
import lk.ijse.greenfiber.entity.Supplies;
import lk.ijse.greenfiber.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuppliesDAOImpl implements SuppliesDAO {

    @Override
    public String generateNextSuppliesId() throws SQLException {

        String sql = "SELECT Supplies_Id FROM supplies ORDER BY Supplies_Id DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private String splitOrderId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] strings = currentOrderId.split("SI0");
            int id = Integer.parseInt(strings[1]);
            id++;

            return "SI0"+id;
        }
        return "SI001";
    }

    @Override
    public Integer getCount() throws SQLException {
        String sql = "SELECT COUNT(Supplies_id) FROM supplies";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public List<String> genarateId() throws SQLException {
        String sql = "SELECT Supplies_id FROM supplies ORDER BY Supplies_id ASC";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String>id = new ArrayList<>();
        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }
        return id;
    }

    @Override
    public Double getAmount(String id) throws SQLException {
        String sql = "SELECT TheOutstandingAmount FROM supplies WHERE Supplies_id = ?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        if (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return 0.0;
    }

    @Override
    public String getSupplierId(String id) throws SQLException {
        String sql = "SELECT Supplier_Id FROM supplies WHERE Supplies_id = ?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean updatePayment(String orderId, Double amount) throws SQLException {
        String sql = "UPDATE supplies SET TheOutstandingAmount = (TheOutstandingAmount - ?) WHERE Supplies_id = ?";
        return CrudUtil.execute(sql,amount,orderId);
    }

    @Override
    public boolean save(Supplies supplies) throws SQLException {
        String sql ="INSERT INTO supplies (Supplies_Id,Amount,Date,Time,Supplier_Id,TheOutstandingAmount) VALUES (?,?,?,?,?,?)";
        return CrudUtil.execute(sql,supplies.getSuppliesId(),supplies.getAmount(),supplies.getDate(),supplies.getTime(),supplies.getSupplierId(),supplies.getAmount());
    }
}
