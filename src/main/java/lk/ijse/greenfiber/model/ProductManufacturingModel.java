//package lk.ijse.greenfiber.model;
//
//import lk.ijse.greenfiber.db.DBConnection;
//import lk.ijse.greenfiber.dto.CartManufactuaringDto;
//import lk.ijse.greenfiber.dto.Product;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//public class ProductManufacturingModel {
//    public static boolean save(Product product, String code, ArrayList<CartManufactuaringDto> manufacturingDto1) throws SQLException {
//
//
//        Connection con = null;
//        try {
//            con = DBConnection.getInstance().getConnection();
//
//            con.setAutoCommit(false);
//
//            boolean isSave = ProductModel.save(product);
//            if (isSave) {
//                boolean isSave1 = ManufacturingCostModel.save(code,manufacturingDto1);
//                if (isSave1) {
//                    con.commit();
//                    return true;
//                }
//            }
//            return false;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            con.rollback();
//            return false;
//        } finally {
//            System.out.println("finally");
//            con.setAutoCommit(true);
//        }
//    }
//}
