package lk.ijse.greenfiber.dao.custom.impl;

import lk.ijse.greenfiber.dao.custom.OrderDetailDAO;
import lk.ijse.greenfiber.entity.OrderDetail;
import lk.ijse.greenfiber.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public boolean save(String orderId, List<OrderDetail> orderDtoList) throws SQLException {
        for (OrderDetail dto : orderDtoList){
            if (!save(orderId,dto)){
                return false;
            }
        }
        return true;
    }

    private static boolean save(String orderId,OrderDetail dto) throws SQLException {
        String sql = "INSERT INTO order_detail (Order_Id,Product_Code,Product_Qty,Unit_Price,Total)VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql,orderId,dto.getProductCode(),dto.getProductQty(),dto.getUnitPrice(),dto.getTotal());
    }
}
