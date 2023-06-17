package lk.ijse.greenfiber.bo.custom;

import lk.ijse.greenfiber.bo.SuperBO;
import lk.ijse.greenfiber.dto.VehicleDTO;

import java.sql.SQLException;
import java.util.List;

public interface VehicleBO extends SuperBO {

    boolean deleteVehicle(String id) throws SQLException;

    boolean saveVehicle(VehicleDTO vehicle) throws SQLException;

    VehicleDTO searchVehicle(String id)throws SQLException;

    boolean updateVehicle(VehicleDTO vehicle)throws SQLException;

    List<VehicleDTO> getAllVehical() throws SQLException;
}
