//package lk.ijse.greenfiber.model;
//
//import lk.ijse.greenfiber.dto.CartManufactuaringDto;
//import lk.ijse.greenfiber.dto.tm.ManufacturingCostTm;
//import lk.ijse.greenfiber.util.CrudUtil;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public class ManufacturingCostModel {
//
//
//    public static boolean save(String pCode, List<CartManufactuaringDto> manufacturingDto) throws SQLException {
//
//        for (CartManufactuaringDto dto : manufacturingDto){
//            if(!save1(pCode, dto)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private static boolean save1(String pCode, CartManufactuaringDto dto) throws SQLException {
//       String sql="INSERT INTO production_detail(Product_Code,Mattirial_Code,Mattirial_Qty,Mattirial_Cost) VALUES (?,?,?,?)";
//        return CrudUtil.execute(sql,pCode,dto.getMattirialCode(),dto.getQty(),dto.getTotal());
//    }
//}
