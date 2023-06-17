package lk.ijse.greenfiber.dao.custom;

import lk.ijse.greenfiber.dao.CrudDAO;
import lk.ijse.greenfiber.entity.Vehicle;

import java.sql.SQLException;

public interface VehicleDAO extends CrudDAO<Vehicle,String> {

    public String getType(String id) throws SQLException ;
}
