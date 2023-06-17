package lk.ijse.greenfiber.dao;

import lk.ijse.greenfiber.bo.custom.impl.AttendenceBOImpl;
import lk.ijse.greenfiber.bo.custom.impl.CustomerBOImpl;
import lk.ijse.greenfiber.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }
    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOTypes {
     DISTRIBUTE,ATTENDANCE,CUSTOMER,EMPLOYEE,MATERIAL,ORDERDETAIL,ORDERS,PAYMENT,PRODUCT,PRODUCTIONDETAIL,SUPPLIER,SUPLIES,SUPPLIERSDETAIL,USER,VEHICLE
    }
    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case ATTENDANCE:
                return new AttendenceDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case MATERIAL:
                return new MaterialDAOImpl();
            case ORDERDETAIL:
                return new OrderDetailDAOImpl();
            case ORDERS:
                return new OrderDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
            case PRODUCTIONDETAIL:
                return new ProductionDetailDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();

            case SUPLIES:
                return new SuppliesDAOImpl();
            case DISTRIBUTE:
                return new DistributeDAOImpl();

            case SUPPLIERSDETAIL:
                return new SuppliesDetailDAOImpl();
            case USER:
                return new UserDAOImpl();
            case VEHICLE :
                return new VehicleDAOImpl();
            default:
                return null;
        }
    }
}
