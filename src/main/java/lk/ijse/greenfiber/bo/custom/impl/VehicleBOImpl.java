package lk.ijse.greenfiber.bo.custom.impl;

import lk.ijse.greenfiber.bo.custom.VehicleBO;
import lk.ijse.greenfiber.dao.DAOFactory;
import lk.ijse.greenfiber.dao.custom.VehicleDAO;
import lk.ijse.greenfiber.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.greenfiber.dto.VehicleDTO;
import lk.ijse.greenfiber.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleBOImpl implements VehicleBO {

    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);

    @Override
    public boolean deleteVehicle(String id) throws SQLException {
        return vehicleDAO.delete(id);
    }

    @Override
    public boolean saveVehicle(VehicleDTO dto) throws SQLException {
        return vehicleDAO.save(new Vehicle(dto.getId(),dto.getNo(),dto.getType(),dto.getColor(),dto.getName()));
    }

    @Override
    public VehicleDTO searchVehicle(String id) throws SQLException {
            Vehicle vehicle = vehicleDAO.search(id);
            return new VehicleDTO(vehicle.getId(),vehicle.getNo(),vehicle.getType(),vehicle.getColor(),vehicle.getName());
    }

    @Override
    public boolean updateVehicle(VehicleDTO dto) throws SQLException {
        return vehicleDAO.update(new Vehicle(dto.getId(),dto.getNo(),dto.getType(),dto.getColor(),dto.getName()));
    }

    @Override
    public List<VehicleDTO> getAllVehical() throws SQLException {
        List<Vehicle> vehicles =vehicleDAO.getAll();
        List<VehicleDTO> vehicleDTOS =new ArrayList<>();

        for (Vehicle vehicle :vehicles){
            vehicleDTOS.add(new VehicleDTO(vehicle.getId(),vehicle.getNo(),vehicle.getType(),vehicle.getColor(),vehicle.getName()));

        }
        return vehicleDTOS;
    }
}
