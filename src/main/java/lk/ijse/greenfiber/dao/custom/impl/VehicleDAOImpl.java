package lk.ijse.greenfiber.dao.custom.impl;

import lk.ijse.greenfiber.dao.custom.VehicleDAO;
import lk.ijse.greenfiber.entity.Vehicle;
import lk.ijse.greenfiber.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {

    @Override
    public List<String> genarateId() throws SQLException {
        String sql = "SELECT Vehicle_Id FROM vehicle";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String>id = new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }
        return id;
    }

    @Override
    public String getType(String id) throws SQLException {
        String sql = "SELECT Vehicle_Type FROM vehicle WHERE Vehicle_Id = ?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        if (resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean save(Vehicle vehicle) throws SQLException {
        Vehicle vehicle1 = search(vehicle.getId());
        if (vehicle1 != null){
            return false;
        }
        String sql = "INSERT INTO vehicle (Vehicle_Id, Vehicle_Number_Plat, Vehicle_Type, Color, Owner_Name) VALUES (?,?,?,?,?)";
        return CrudUtil.execute(sql,vehicle.getId(),vehicle.getNo(),vehicle.getType(),vehicle.getColor(),vehicle.getName());
    }

    @Override
    public boolean update(Vehicle vehicle) throws SQLException {
        String sql = "UPDATE vehicle SET Vehicle_Number_Plat = ?, Vehicle_Type = ?, Color = ?, Owner_Name = ? WHERE Vehicle_Id = ?";
        return CrudUtil.execute(sql,vehicle.getNo(),vehicle.getType(),vehicle.getColor(),vehicle.getName(),vehicle.getId());
    }

    @Override
    public Vehicle search(String id) throws SQLException {
        String sql = "SELECT * FROM vehicle WHERE Vehicle_Id = ?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        if (resultSet.next()){
            return new Vehicle(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM vehicle WHERE  Vehicle_Id = ? ";
        return CrudUtil.execute(sql,id);
    }

    @Override
    public List<Vehicle> getAll() throws SQLException {
        String sql = "SELECT * FROM vehicle";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<Vehicle>vehicleList = new ArrayList<>();

        while (resultSet.next()){
            vehicleList.add(new Vehicle(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return vehicleList;
    }
}
