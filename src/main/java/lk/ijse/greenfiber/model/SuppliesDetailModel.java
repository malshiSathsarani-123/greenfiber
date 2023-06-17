//package lk.ijse.greenfiber.model;
//
//import lk.ijse.greenfiber.dto.CartSuppliesDto;
//import lk.ijse.greenfiber.util.CrudUtil;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public class SuppliesDetailModel {
//    public static boolean save(String suppliesId, List<CartSuppliesDto> dtoList) throws SQLException {
//        for (CartSuppliesDto dto : dtoList){
//            if (!save(suppliesId,dto)){
//                return false;
//            }
//        }
//        return true;
//    }
//    public static boolean save(String suppliesId, CartSuppliesDto dto) throws SQLException {
//        String sql = "INSERT INTO supplies_detail (Supplies_Id,Mattirial_Code,Mattirial_Qty,Unit_Price,Total) VALUES (?,?,?,?,?)";
//        return CrudUtil.execute(sql,suppliesId,dto.getCode(),dto.getQty(),dto.getUnitPrice(),dto.getTotal());
//    }
//}
