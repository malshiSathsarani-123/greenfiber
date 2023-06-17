package lk.ijse.greenfiber.bo.custom;

import lk.ijse.greenfiber.bo.SuperBO;
import lk.ijse.greenfiber.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {

    String getNextId() throws SQLException;

    List<String> genarateSuppliesId() throws SQLException;

    List<String> genarateOrderId() throws SQLException;

    String getCustomerIdOrder(String id) throws SQLException;

    Double getAmountOrder(String id) throws SQLException;

    Double getBalanceOrder()throws SQLException;

    boolean allSaveOrder(PaymentDTO dto) throws SQLException;

    String getSupplierId(String id) throws SQLException;

    Double getAmountSupplies(String id) throws SQLException;

    Double getBalanceSupplies() throws SQLException;

    boolean allSaveSupplies(PaymentDTO dto) throws SQLException;

    List<PaymentDTO> getAllPayment() throws SQLException;
}
