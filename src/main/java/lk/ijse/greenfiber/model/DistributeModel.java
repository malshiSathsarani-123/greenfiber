//package lk.ijse.greenfiber.model;
//
//import lk.ijse.greenfiber.util.CrudUtil;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class DistributeModel {
//
//    public static String getNextDeliveryId() throws SQLException {
//        String sql = "SELECT deliveryId FROM orders ORDER BY deliveryId DESC LIMIT 1";
//        ResultSet resultSet = CrudUtil.execute(sql);
//
//        if (resultSet.next()){
//            return nextId(resultSet.getString(1));
//        }
//        return nextId(null);
//    }
//    private static String nextId(String currentId) {
//        if (currentId != null){
//            String[] strings = currentId.split("D0");
//            int id = Integer.parseInt(strings[1]);
//            id++;
//
//            return "D0"+id;
//        }
//        return "D001";
//    }
//}
