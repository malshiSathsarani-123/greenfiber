//package lk.ijse.greenfiber.model;
//
//import lk.ijse.greenfiber.dto.CartOrderDto;
//import lk.ijse.greenfiber.util.CrudUtil;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public class OrderDetailModel {
//    public static boolean save(String orderId, List<CartOrderDto> orderDtoList) throws SQLException {
//        for (CartOrderDto dto : orderDtoList){
//            if (!save(orderId,dto)){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private static boolean save(String orderId,CartOrderDto dto) throws SQLException {
//        String sql = "INSERT INTO order_detail (Order_Id,Product_Code,Product_Qty,Unit_Price,Total)VALUES(?,?,?,?,?)";
//        return CrudUtil.execute(sql,orderId,dto.getCode(),dto.getQty(),dto.getUnitPrice(),dto.getTotal());
//    }
//}
