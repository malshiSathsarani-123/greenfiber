//package lk.ijse.greenfiber.model;
//
//import lk.ijse.greenfiber.db.DBConnection;
//import lk.ijse.greenfiber.dto.CartOrderDto;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//
//public class PlaceOrderModel {
//    public static boolean save(String orderId, String amount, String customerId, List<CartOrderDto> orderDtoList) throws SQLException {
//        Connection connection = null;
//
//        try {
//            connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//
//            boolean isSave = OrderModel.save(orderId,amount, LocalDate.now(), LocalTime.now(),customerId);
//            if (isSave){
//                boolean isUpdate = ProductModel.updateQtyOrder(orderDtoList);
//                if (isUpdate){
//                    boolean isSaveOrderDetail = OrderDetailModel.save(orderId,orderDtoList);
//                    if (isSaveOrderDetail){
//                        connection.commit();
//                        return true;
//                    }
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
//}
