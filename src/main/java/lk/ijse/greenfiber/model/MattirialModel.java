//package lk.ijse.greenfiber.model;
//
//import lk.ijse.greenfiber.dto.CartMaterialDto;
//import lk.ijse.greenfiber.dto.CartSuppliesDto;
//import lk.ijse.greenfiber.dto.Matirial;
//import lk.ijse.greenfiber.dto.Supplier;
//import lk.ijse.greenfiber.util.CrudUtil;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MattirialModel {
////    public static boolean save(Matirial matirial) throws SQLException {
////        try {
////            Matirial matirial1 = search(matirial.getMattirial_Id());
////            if (matirial1 != null) {
////                return false;
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        String sql = "INSERT INTO mattirial (Mattirial_Code, Description, Qty_On_Hand, Unit_Price) VALUES (?,?,?,?)";
////        return CrudUtil.execute(sql,matirial.getMattirial_Id(),matirial.getDescription(),matirial.getQty_on_hand(),matirial.getUnit_price());
////    }
////
////    public static boolean delete(String id) throws SQLException {
////        String sql = "DELETE FROM mattirial WHERE  Mattirial_Code = ? ";
////        return  CrudUtil.execute(sql, id);
////
////    }
////
////    public static Matirial search(String id) throws SQLException {
////        String sql = "SELECT * FROM mattirial WHERE Mattirial_Code = ?";
////        ResultSet resultSet = CrudUtil.execute(sql, id);
////
////        if (resultSet.next()){
////            String mId = resultSet.getString(1);
////            String description = resultSet.getString(2);
////            Integer qty_on_hand = Integer.valueOf(resultSet.getString(3));
////            Double unitPrice = Double.valueOf(resultSet.getString(4));
////
////            return new Matirial(mId,description,qty_on_hand,unitPrice);
////        }
////        return null;
////    }
////
////    public static boolean update(Matirial matirial) throws SQLException {
////        String sql = "UPDATE mattirial SET Description = ?, Qty_On_Hand = ?, Unit_Price = ? WHERE Mattirial_Code = ?";
////        return CrudUtil.execute(sql,matirial.getDescription(),matirial.getQty_on_hand(),matirial.getUnit_price(), matirial.getMattirial_Id());
////    }
////
////    public static List<String> generateCode() throws SQLException {
////        String sql = "SELECT Mattirial_Code FROM mattirial";
////        ResultSet resultSet = CrudUtil.execute(sql);
////
////        List<String>codes = new ArrayList<>();
////
////        while (resultSet.next()){
////            codes.add(resultSet.getString(1));
////        }
////        return codes;
////    }
////
////    public static String getName(String code) throws SQLException {
////        String sql = "SELECT description FROM mattirial WHERE Mattirial_Code = ?";
////        ResultSet resultSet = CrudUtil.execute(sql,code);
////
////        String name = "";
////        if (resultSet.next()){
////             name = resultSet.getString(1);
////        }
////        return name;
////    }
////
////    public static Double getUnitPrice(String mCode) throws SQLException {
////        String sql = "SELECT Unit_Price FROM mattirial WHERE Mattirial_Code = ?";
////        ResultSet resultSet = CrudUtil.execute(sql,mCode);
////
////        Double unitPrice = 0.0;
////        if (resultSet.next()){
////            unitPrice = Double.valueOf(resultSet.getString(1));
////        }
////        return unitPrice;
////    }
////
////    public static boolean updateQty(List<CartMaterialDto> cartMaterialDtoList) throws SQLException {
////        for (CartMaterialDto dto : cartMaterialDtoList){
////            if (!updateQty(dto)){
////                return false;
////            }
////        }
////        return true;
////    }
////
////    private static boolean updateQty(CartMaterialDto dto) throws SQLException {
////        String sql1 = "SELECT Qty_On_Hand FROM mattirial WHERE Mattirial_Code = ?";
////        ResultSet resultSet = CrudUtil.execute(sql1, dto.getMaterialCode());
////        if (resultSet.next()) {
////            Integer qty = resultSet.getInt(1);
////            if (qty > dto.getQty()) {
////                String sql = "UPDATE mattirial SET Qty_On_Hand = (Qty_On_Hand - ?) WHERE Mattirial_Code = ?";
////                return CrudUtil.execute(sql, dto.getQty(), dto.getMaterialCode());
////            }
////        }
////        return false;
////    }
////
////    public static boolean updateQtySupplies(List<CartSuppliesDto> dtoList) throws SQLException {
////        for (CartSuppliesDto dto : dtoList){
////            if(!updateQtySupplies1(dto)){
////                return false;
////            }
////        }
////        return true;
////    }
////
////    private static boolean updateQtySupplies1(CartSuppliesDto dto) throws SQLException {
////        String sql = "UPDATE mattirial SET Qty_On_Hand = (Qty_On_Hand + ?) WHERE Mattirial_Code = ?";
////        return CrudUtil.execute(sql,dto.getQty(),dto.getCode());
////    }
////
////    public static Matirial getLastMattirialData() throws SQLException {
////        String sql = "SELECT * FROM mattirial ORDER BY Mattirial_Code DESC LIMIT 1";
////        ResultSet resultSet = CrudUtil.execute(sql);
////
////        if (resultSet.next()){
////            String code = resultSet.getString(1);
////            String description = resultSet.getString(2);
////            int qty = resultSet.getInt(3);
////            double price = resultSet.getDouble(4);
////
////            return new Matirial(code,description,qty,price);
////        }
////        return null;
////    }
////
////    public static List<Matirial> getAll() throws SQLException {
////        String sql = "SELECT * FROM mattirial";
////        ResultSet resultSet = CrudUtil.execute(sql);
////        List<Matirial>matirialList = new ArrayList<>();
////
////        while (resultSet.next()){
////            matirialList.add(new Matirial(
////                    resultSet.getString(1),
////                    resultSet.getString(2),
////                    resultSet.getInt(3),
////                    resultSet.getDouble(4)
////            ));
////        }
////        return matirialList;
////    }
//}
