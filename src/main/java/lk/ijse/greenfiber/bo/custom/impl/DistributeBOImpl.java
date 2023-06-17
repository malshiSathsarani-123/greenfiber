package lk.ijse.greenfiber.bo.custom.impl;

import lk.ijse.greenfiber.bo.custom.DistributeBO;
import lk.ijse.greenfiber.dao.DAOFactory;
import lk.ijse.greenfiber.dao.custom.DistributeDAO;
import lk.ijse.greenfiber.dao.custom.EmployeeDAO;
import lk.ijse.greenfiber.dao.custom.OrderDAO;
import lk.ijse.greenfiber.dao.custom.VehicleDAO;
import lk.ijse.greenfiber.dao.custom.impl.DistributeDAOImpl;
import lk.ijse.greenfiber.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.greenfiber.dao.custom.impl.OrderDAOImpl;
import lk.ijse.greenfiber.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.greenfiber.dto.OrdersDTO;
import lk.ijse.greenfiber.entity.Orders;

import java.sql.SQLException;
import java.util.List;

public class DistributeBOImpl implements DistributeBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERS);
    DistributeDAO distributeDAO = (DistributeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DISTRIBUTE);
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public List<String> genarateOrderId() throws SQLException {
        return orderDAO.genarateIdNotDelivery();
    }

    @Override
    public List<String> genarateDriversId() throws SQLException{
        return employeeDAO.genarateId();
    }

    @Override
    public List<String> genarateVehicleId() throws SQLException{
        return vehicleDAO.genarateId();
    }

    @Override
    public String getNextDeliveryId() throws SQLException{
        return distributeDAO.getNextDeliveryId();
    }

    @Override
    public boolean updateOrder(OrdersDTO dto) throws SQLException{
        return orderDAO.update(new Orders(dto.getDeliveryId(),dto.getEmployeeId(),dto.getVehicleId(),dto.getOrderId()));
    }

    @Override
    public String getDriverName(String DriverId) throws SQLException {
        return employeeDAO.getName(DriverId);
    }

    @Override
    public String getVehicleType(String vehicleId) throws SQLException{
        return vehicleDAO.getType(vehicleId);
    }

    @Override
    public String getOrderId() throws SQLException {
        return orderDAO.getOrderId();
    }
}
