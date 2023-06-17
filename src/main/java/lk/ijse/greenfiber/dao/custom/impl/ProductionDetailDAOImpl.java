package lk.ijse.greenfiber.dao.custom.impl;

import lk.ijse.greenfiber.dao.custom.ProductionDetailDAO;
import lk.ijse.greenfiber.entity.ProductionDetail;
import lk.ijse.greenfiber.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductionDetailDAOImpl implements ProductionDetailDAO {

    @Override
    public boolean save(List<ProductionDetail> productionDetails) throws SQLException {
        for (ProductionDetail dto : productionDetails){
            if(!save(dto)) {
                return false;
            }
        }
        return true;
    }

    public boolean save(ProductionDetail productionDetails) throws SQLException {
       String sql="INSERT INTO production_detail(Product_Code,Mattirial_Code,Mattirial_Qty,Mattirial_Cost) VALUES (?,?,?,?)";
        return CrudUtil.execute(sql,productionDetails.getProduct_Code(),productionDetails.getMaterial_Code(),productionDetails.getMaterial_Qty(),productionDetails.getMaterial_Cost());
    }

    @Override
    public  List<ProductionDetail> getAll(String code) throws SQLException {
        String sql = "SELECT * FROM production_detail WHERE Product_Code = ?";
        List<ProductionDetail> data = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql,code);
        while (resultSet.next()) {
            String pCode = resultSet.getString(1);
            String mCode = resultSet.getString(2);
            int qty = resultSet.getInt(3);
            double total = resultSet.getDouble(4);

            data.add(new ProductionDetail(pCode,mCode,qty,total));
        }
        return data;
    }
}
