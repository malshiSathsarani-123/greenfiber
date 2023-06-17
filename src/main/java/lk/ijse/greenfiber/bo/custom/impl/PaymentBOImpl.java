package lk.ijse.greenfiber.bo.custom.impl;

import lk.ijse.greenfiber.bo.custom.PaymentBO;
import lk.ijse.greenfiber.dao.DAOFactory;
import lk.ijse.greenfiber.dao.custom.OrderDAO;
import lk.ijse.greenfiber.dao.custom.PaymentDAO;
import lk.ijse.greenfiber.dao.custom.SuppliesDAO;
import lk.ijse.greenfiber.dao.custom.impl.OrderDAOImpl;
import lk.ijse.greenfiber.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.greenfiber.dao.custom.impl.SuppliesDAOImpl;
import lk.ijse.greenfiber.db.DBConnection;
import lk.ijse.greenfiber.dto.PaymentDTO;
import lk.ijse.greenfiber.entity.Payment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    SuppliesDAO suppliesDAO = (SuppliesDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPLIES);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERS);

    @Override
    public String getNextId() throws SQLException {
        return paymentDAO.getNextId();
    }

    @Override
    public List<String> genarateSuppliesId() throws SQLException {
        return suppliesDAO.genarateId();
    }

    @Override
    public List<String> genarateOrderId() throws SQLException {
        return orderDAO.genarateId();
    }

    @Override
    public String getCustomerIdOrder(String id) throws SQLException {
        return orderDAO.getCustomerId(id);
    }

    @Override
    public Double getAmountOrder(String id) throws SQLException {
        return orderDAO.getAmount(id);
    }

    @Override
    public Double getBalanceOrder() throws SQLException {
        return paymentDAO.getBalance();
    }

    @Override
    public boolean allSaveOrder(PaymentDTO dto) throws SQLException {
        Connection connection = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean isSave = paymentDAO.saveOrder(new Payment(dto.getPayId(),dto.getDate(),dto.getTime(),dto.getAmount(),dto.getBalance(),dto.getOrderId(),dto.getSuppliesId(),dto.getType()));
            if (isSave){
                boolean isUpdate = orderDAO.updatePayment(dto.getOrderId(),dto.getAmount());
                if (isUpdate){
                    connection.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public String getSupplierId(String id) throws SQLException {
        return suppliesDAO.getSupplierId(id);
    }

    @Override
    public Double getAmountSupplies(String id) throws SQLException {
        return suppliesDAO.getAmount(id);
    }

    @Override
    public Double getBalanceSupplies() throws SQLException {
        return paymentDAO.getBalance();
    }

    @Override
    public boolean allSaveSupplies(PaymentDTO dto) throws SQLException {
        Connection connection = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean isSave = paymentDAO.saveSupplies(new Payment(dto.getPayId(),dto.getDate(),dto.getTime(),dto.getAmount(),dto.getBalance(),dto.getOrderId(),dto.getSuppliesId(),dto.getType()));
            if (isSave){
                boolean isUpdate = suppliesDAO.updatePayment(dto.getSuppliesId(),dto.getAmount());
                if (isUpdate){
                    connection.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public List<PaymentDTO> getAllPayment() throws SQLException {
        List<Payment> paymen =paymentDAO.getAll();
        List<PaymentDTO> paymentDTOS =new ArrayList<>();

        for (Payment payment : paymen){
            paymentDTOS.add(new PaymentDTO(payment.getPayId(),payment.getDate(),payment.getTime(),payment.getAmount(),payment.getBalance(),payment.getOrderId(),payment.getSuppliesId(),payment.getType()));

        }
        return paymentDTOS;
    }
}
