//package lk.ijse.greenfiber.model;
//
//import lk.ijse.greenfiber.db.DBConnection;
//import lk.ijse.greenfiber.dto.CartMaterialDto;
//import lk.ijse.greenfiber.dto.ManufacturingCost;
//import lk.ijse.greenfiber.dto.Product;
//import lk.ijse.greenfiber.dto.ProductMake;
//import lk.ijse.greenfiber.util.CrudUtil;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ManufacturingModel {
//    public static ProductMake getDescription(String code) throws SQLException {
//
//        String sql = "SELECT Description,Cost FROM product WHERE product_code =?";
//        ResultSet resultSet = CrudUtil.execute(sql,code);
//
//
//        if (resultSet.next()){
//            String descrription = resultSet.getString(1);
//            Double cost =resultSet.getDouble(2);
//
//            return new ProductMake(descrription, cost);
//        }
//        return null;
//
//    }
//
//    public static List<ManufacturingCost> getAll(String code) throws SQLException {
//
//        String sql = "SELECT * FROM production_detail WHERE Product_Code = ?";
//
//        List<ManufacturingCost> data = new ArrayList<>();
//
//       ResultSet resultSet = CrudUtil.execute(sql,code);
//        while (resultSet.next()) {
//            String pCode = resultSet.getString(1);
//            String mCode = resultSet.getString(2);
//            int qty = resultSet.getInt(3);
//            double total = resultSet.getDouble(4);
//
//            data.add(new ManufacturingCost(pCode,mCode,qty,total));
//
//        }
//        return data;
//    }
//
//    public static boolean update(String pCode, String pQty, List<CartMaterialDto> cartMaterialDtoList) throws SQLException {
//        Connection connection=null;
//        try {
//             connection = DBConnection.getInstance().getConnection();
//            connection.setAutoCommit(false);
//
//            boolean isUpdate = MattirialModel.updateQty(cartMaterialDtoList);
//            if (isUpdate){
//                boolean isUpdateProduct = ProductModel.updateQty(pCode,pQty);
//                if (isUpdateProduct){
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
//}
