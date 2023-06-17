//package lk.ijse.greenfiber.model;
//
//import lk.ijse.greenfiber.db.DBConnection;
//import lk.ijse.greenfiber.dto.CartSuppliesDto;
//import lk.ijse.greenfiber.util.CrudUtil;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//
//public class PlaceSuppliesModel {
//
//    public static boolean save(String suppliesId, String supplierId, String amount, List<CartSuppliesDto> dtoList) throws SQLException {
//        Connection connection = null;
//
//        try {
//            connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//
//            boolean isSave = SuppliesModel.save(suppliesId,amount, LocalDate.now(), LocalTime.now(),supplierId);
//                if (isSave){
//                   boolean isUpdated = MattirialModel.updateQtySupplies(dtoList);
//                   if (isUpdated){
//                       boolean isSaveSuppliesDetail = SuppliesDetailModel.save(suppliesId,dtoList);
//                       if (isSaveSuppliesDetail) {
//                           connection.commit();
//                           return true;
//                       }
//                   }
//                }
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
//
//
//
//
//
