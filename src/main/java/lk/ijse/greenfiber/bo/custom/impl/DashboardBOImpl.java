package lk.ijse.greenfiber.bo.custom.impl;

import lk.ijse.greenfiber.bo.custom.DashboardBO;
import lk.ijse.greenfiber.dao.DAOFactory;
import lk.ijse.greenfiber.dao.custom.*;
import lk.ijse.greenfiber.dao.custom.impl.*;

import java.sql.SQLException;

public class DashboardBOImpl implements DashboardBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    AttendenceDAO attendenceDAO = (AttendenceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ATTENDANCE);
    SuppliesDAO suppliesDAO = (SuppliesDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPLIES);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERS);
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

//    PaymentDAO paymentDAO =new PaymentDAOImpl();
//    AttendenceDAO attendenceDAO=new AttendenceDAOImpl();
//    SuppliesDAO suppliesDAO =new SuppliesDAOImpl();
//    OrderDAO orderDAO =new OrderDAOImpl();

    @Override
    public Double getBalance() throws SQLException {
        return paymentDAO.getBalance();
    }

    @Override
    public Integer getAttendenceCount() throws SQLException {
        return attendenceDAO.getCount();
    }

    @Override
    public Integer getAbsentCount() throws SQLException {
        return attendenceDAO.getAbsentCount();
    }

    @Override
    public Integer getSuppliesCount() throws SQLException {
        return suppliesDAO.getCount();
    }

    @Override
    public Integer getSupplierCount() throws SQLException {
        return supplierDAO.getCount();
    }

    @Override
    public Integer getOrderCount() throws SQLException {
        return orderDAO.getCount();
    }

    @Override
    public Integer getCustomerCount() throws SQLException {
        return customerDAO.getCount();
    }
}
