package lk.ijse.greenfiber.dao.custom;

import lk.ijse.greenfiber.dao.CrudDAO;
import lk.ijse.greenfiber.entity.OrderDetail;
import lk.ijse.greenfiber.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO extends CrudDAO<Product,String> {

     boolean updateQty(String pCode, String pQty) throws SQLException ;

     boolean updateQtyOrder(List<OrderDetail> orderDtoList) throws SQLException ;

    boolean updateQtyOrder(OrderDetail dto) throws SQLException ;

     List<Product> getAll() throws SQLException ;

     Product getDescription(String code) throws SQLException ;
}
