package lk.ijse.greenfiber.dao.custom.impl;

import lk.ijse.greenfiber.dao.custom.ProductDAO;
import lk.ijse.greenfiber.entity.OrderDetail;
import lk.ijse.greenfiber.entity.Product;
import lk.ijse.greenfiber.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public boolean save(Product product) throws SQLException {
        try {
            Product product1= search(product.getProductCode());
            if (product1 != null) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "INSERT INTO product (Product_Code, Description, Qty_On_Hand, Cost, Unit_Price) VALUES (?,?,?,?,?)";
        return CrudUtil.execute(sql,product.getProductCode(),product.getDescription(),product.getQtyOnHand(),product.getCost(),product.getUnitPrice());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM product WHERE  Product_Code = ? ";
        return  CrudUtil.execute(sql, id);
    }

    @Override
    public Product search(String id) throws SQLException {
        String sql = "SELECT * FROM product WHERE Product_Code = ?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        if (resultSet.next()){
            String pId = resultSet.getString(1);
            String description = resultSet.getString(2);
            Integer qty = Integer.valueOf(resultSet.getString(3));
            Double cost = Double.valueOf(resultSet.getString(4));
            Double unitPrice = Double.valueOf(resultSet.getString(5));

           return new Product(pId,description,qty,cost,unitPrice);
        }
        return null;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        String sql = "UPDATE product SET Description = ?, Qty_On_Hand = ?, Cost = ?,  Unit_Price = ? WHERE Product_Code = ?";
        return CrudUtil.execute(sql,product.getDescription(),product.getQtyOnHand(),product.getCost(),product.getUnitPrice(), product.getProductCode());
    }

    @Override
    public List<String> genarateId() throws SQLException {
        String sql = "SELECT Product_Code FROM Product";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String>code=new ArrayList<>();

        while (resultSet.next()){
           code.add(resultSet.getString(1));
        }
        return code;
    }

    @Override
    public boolean updateQty(String pCode, String pQty) throws SQLException {
        String sql = "UPDATE product SET Qty_On_Hand = (Qty_On_Hand + ?) WHERE Product_Code = ?";
        return CrudUtil.execute(sql,pQty,pCode);
    }

    @Override
    public boolean updateQtyOrder(List<OrderDetail> orderDtoList) throws SQLException {
        for (OrderDetail dto : orderDtoList) {
            if (!updateQtyOrder(dto)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateQtyOrder(OrderDetail dto) throws SQLException {
        String sql = "UPDATE product SET Qty_On_Hand = (Qty_On_Hand - ?) WHERE Product_Code = ?";
        return CrudUtil.execute(sql,dto.getProductQty(),dto.getProductCode());
    }

    @Override
    public List<Product> getAll() throws SQLException {
        String sql = "SELECT * FROM product";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<Product>productList = new ArrayList<>();

        while (resultSet.next()){
            productList.add(new Product(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5)
            ));
        }
        return productList;
    }

    @Override
    public Product getDescription(String code) throws SQLException {

        String sql = "SELECT Description,Cost FROM product WHERE product_code =?";
        ResultSet resultSet = CrudUtil.execute(sql,code);

        if (resultSet.next()){
            String description = resultSet.getString(1);
            Double cost =resultSet.getDouble(2);

            return new Product(description, cost);
        }
        return null;

    }
}
