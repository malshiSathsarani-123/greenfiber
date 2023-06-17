//package lk.ijse.greenfiber.model;
//
//import lk.ijse.greenfiber.util.CrudUtil;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class OrderModel {
//    public static String getNextId() throws SQLException {
//        String sql = "SELECT Order_id FROM orders ORDER BY Order_id DESC LIMIT 1";
//        ResultSet resultSet = CrudUtil.execute(sql);
//
//        if (resultSet.next()){
//           return nextId(resultSet.getString(1));
//        }
//        return nextId(null);
//    }
//
//    private static String nextId(String currentId) {
//        if (currentId != null){
//            String[] strings = currentId.split("O0");
//            int id = Integer.parseInt(strings[1]);
//            id++;
//
//            return "O0"+id;
//        }
//     return "O001";
//    }
//
//    public static boolean save(String orderId, String amount, LocalDate date, LocalTime time, String customerId) throws SQLException {
//        String sql = "INSERT INTO orders (Order_Id,Amount,Date,Time,Customer_Id,TheOutstandingAmount) VALUES (?,?,?,?,?,?)";
//        return CrudUtil.execute(sql,orderId,amount,date,time,customerId,amount);
//    }
//
//    public static String getOrderId() throws SQLException {
//        String sql = "SELECT Order_id FROM orders ORDER BY Order_id DESC LIMIT 1";
//        ResultSet resultSet = CrudUtil.execute(sql);
//
//        if (resultSet.next()){
//            String id = resultSet.getString(1);
//            return id;
//        }
//        return "O001";
//    }
//
//    public static boolean update(String deliveryId, String driverId, String vehicleId,String id) throws SQLException {
//        String sql ="UPDATE orders SET deliveryId = ?, Employee_Id = ?, Vehicle_Id = ? WHERE Order_id = ?";
//        return CrudUtil.execute(sql,deliveryId,driverId,vehicleId,id);
//    }
//
//    public static List<String> genarateId() throws SQLException {
//        String sql = "SELECT Order_id FROM orders ORDER BY Order_id ASC";
//        ResultSet resultSet = CrudUtil.execute(sql);
//
//        List<String>id = new ArrayList<>();
//
//        while (resultSet.next()){
//            id.add(resultSet.getString(1));
//        }
//        return id;
//    }
//    public static List<String> genarateIdOK() throws SQLException {
//        String sql = "SELECT Order_id FROM orders WHERE deliveryId IS NULL ORDER BY Order_id ASC";
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
//    public static Integer getCount() throws SQLException {
//        String sql = "SELECT COUNT(Order_id) FROM orders";
//        ResultSet resultSet = CrudUtil.execute(sql);
//        if (resultSet.next()){
//            return resultSet.getInt(1);
//        }
//        return 0;    }
//
//    public static Double getAmount(String id) throws SQLException {
//        String sql = "SELECT TheOutstandingAmount FROM orders WHERE Order_id = ?";
//        ResultSet resultSet = CrudUtil.execute(sql,id);
//
//        if (resultSet.next()){
//            return resultSet.getDouble(1);
//        }
//        return 0.0;
//    }
//
//    public static String getCustomerId(String id) throws SQLException {
//        String sql = "SELECT Customer_Id FROM orders WHERE Order_id = ?";
//        ResultSet resultSet = CrudUtil.execute(sql,id);
//
//        if (resultSet.next()){
//            return resultSet.getString(1);
//        }
//        return null;
//    }
//
//    public static boolean updatePayment(String orderId, Double amount) throws SQLException {
//        String sql = "UPDATE orders SET TheOutstandingAmount = (TheOutstandingAmount - ?) WHERE Order_id = ?";
//        return CrudUtil.execute(sql,amount,orderId);
//    }
//}
