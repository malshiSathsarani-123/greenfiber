package lk.ijse.greenfiber.dao.custom;

import lk.ijse.greenfiber.dao.SuperDAO;
import lk.ijse.greenfiber.entity.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO  extends SuperDAO {

     boolean saveOrder(Payment payment) throws SQLException ;

     boolean saveSupplies(Payment payment) throws SQLException ;

    public Double getBalance() throws SQLException ;

    public String getNextId() throws SQLException ;

    public List<Payment> getAll() throws SQLException ;
}
