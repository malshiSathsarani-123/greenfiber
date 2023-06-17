//package lk.ijse.greenfiber.model;
//
//import lk.ijse.greenfiber.db.DBConnection;
//import lk.ijse.greenfiber.dto.Payment;
//import lk.ijse.greenfiber.util.CrudUtil;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PaymentModel {
//    public static boolean allSave(Payment payment) throws SQLException {
//        Connection connection = null;
//
//        try {
//            connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//            boolean isSave = save(payment);
//            if (isSave){
//                boolean isUpdate = OrderModel.updatePayment(payment.getOrderId(),payment.getAmount());
//                if (isUpdate){
//                    connection.commit();
//                    return true;
//                }
//            }
//            return false;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            connection.rollback();
//            return false;
//        }finally {
//            connection.setAutoCommit(true);
//        }
//    }
//
//    private static boolean save(Payment payment) throws SQLException {
//        String sql = "INSERT INTO payment (Payment_Id,Date,Time,Amount,Balance,Payment_Type,Order_id) VALUES (?,?,?,?,?,?,?)";
//        return CrudUtil.execute(sql,payment.getPayId(),payment.getDate(),payment.getTime(),payment.getAmount(),payment.getBalance(),payment.getType(),payment.getOrderId());
//    }
//
//    private static boolean save1(Payment payment) throws SQLException {
//        String sql = "INSERT INTO payment (Payment_Id,Date,Time,Amount,Balance,Payment_Type,Supplies_id) VALUES (?,?,?,?,?,?,?)";
//        return CrudUtil.execute(sql,payment.getPayId(),payment.getDate(),payment.getTime(),payment.getAmount(),payment.getBalance(),payment.getType(),payment.getSuppliesId());
//    }
//    public static Double getBalance() throws SQLException {
//        String sql = "SELECT Balance FROM payment ORDER BY Payment_Id DESC LIMIT 1";
//        ResultSet resultSet = CrudUtil.execute(sql);
//
//        if (resultSet.next()){
//            return resultSet.getDouble(1);
//        }
//        return 0.0;
//    }
//
//    public static boolean allSaveSupplies(Payment payment) throws SQLException {
//        Connection connection = null;
//
//        try {
//            connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//            boolean isSave = save1(payment);
//            if (isSave){
//                boolean isUpdate = SuppliesModel.updatePayment(payment.getSuppliesId(),payment.getAmount());
//                if (isUpdate){
//                    connection.commit();
//                    return true;
//                }
//            }
//            return false;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            connection.rollback();
//            return false;
//        }finally {
//            connection.setAutoCommit(true);
//        }
//    }
//    public static String getNextId() throws SQLException {
//        String sql = "SELECT Payment_Id FROM payment ORDER BY Payment_Id DESC LIMIT 1";
//        ResultSet resultSet = CrudUtil.execute(sql);
//
//        if (resultSet.next()){
//            return nextId(resultSet.getString(1));
//        }
//        return nextId(null);
//    }
//
//    private static String nextId(String currentId) {
//        if (currentId != null){
//            String[] strings = currentId.split("P0");
//            int id = Integer.parseInt(strings[1]);
//            id++;
//
//            return "P0"+id;
//        }
//        return "P001";
//    }
//
//    public static List<Payment> getAll() throws SQLException {
//        String sql = "SELECT * FROM payment";
//        ResultSet resultSet = CrudUtil.execute(sql);
//        List<Payment>paymentList = new ArrayList<>();
//
//        while (resultSet.next()){
//            paymentList.add(new Payment(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getTime(3),
//                    resultSet.getDouble(4),
//                    resultSet.getDouble(5),
//                    resultSet.getString(8),
//                    resultSet.getString(6),
//                    resultSet.getString(7)
//                    ));
//        }
//        return paymentList;
//    }
//}
