//package lk.ijse.greenfiber.model;
//
//import lk.ijse.greenfiber.dto.Vehicle;
//import lk.ijse.greenfiber.util.CrudUtil;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class VehicleModel {
//    public static List<String> genarateId() throws SQLException {
//        String sql = "SELECT Vehicle_Id FROM vehicle";
//        ResultSet resultSet = CrudUtil.execute(sql);
//
//        List<String>id = new ArrayList<>();
//
//        while (resultSet.next()){
//            id.add(resultSet.getString(1));
//        }
//        return id;
//    }
//
//    public static String getType(String id) throws SQLException {
//        String sql = "SELECT Vehicle_Type FROM vehicle WHERE Vehicle_Id = ?";
//        ResultSet resultSet = CrudUtil.execute(sql,id);
//
//        if (resultSet.next()){
//            return resultSet.getString(1);
//        }
//        return null;
//    }
//
//    public static boolean save(Vehicle vehicle) throws SQLException {
//
//        Vehicle vehicle1 = search(vehicle.getId());
//        if (vehicle1 != null){
//            return false;
//        }
//        String sql = "INSERT INTO vehicle (Vehicle_Id, Vehicle_Number_Plat, Vehicle_Type, Color, Owner_Name) VALUES (?,?,?,?,?)";
//        return CrudUtil.execute(sql,vehicle.getId(),vehicle.getNo(),vehicle.getType(),vehicle.getColor(),vehicle.getName());
//    }
//
//    public static boolean update(Vehicle vehicle) throws SQLException {
//        String sql = "UPDATE vehicle SET Vehicle_Number_Plat = ?, Vehicle_Type = ?, Color = ?, Owner_Name = ? WHERE Vehicle_Id = ?";
//        return CrudUtil.execute(sql,vehicle.getNo(),vehicle.getType(),vehicle.getColor(),vehicle.getName(),vehicle.getId());
//    }
//
//    public static Vehicle search(String id) throws SQLException {
//        String sql = "SELECT * FROM vehicle WHERE Vehicle_Id = ?";
//        ResultSet resultSet = CrudUtil.execute(sql,id);
//
//        if (resultSet.next()){
//            return new Vehicle(resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getString(5)
//            );
//        }
//        return null;
//    }
//
//    public static boolean delete(String id) throws SQLException {
//        String sql = "DELETE FROM vehicle WHERE  Vehicle_Id = ? ";
//        return CrudUtil.execute(sql,id);
//    }
//
//    public static List<Vehicle> getAll() throws SQLException {
//        String sql = "SELECT * FROM vehicle";
//        ResultSet resultSet = CrudUtil.execute(sql);
//        List<Vehicle>vehicleList = new ArrayList<>();
//
//        while (resultSet.next()){
//            vehicleList.add(new Vehicle(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getString(5)
//            ));
//        }
//        return vehicleList;
//    }
//}
