package lk.ijse.greenfiber.dao.custom.impl;

import lk.ijse.greenfiber.dao.custom.SuppliesDetailDAO;
import lk.ijse.greenfiber.entity.SuppliesDetail;
import lk.ijse.greenfiber.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class SuppliesDetailDAOImpl implements SuppliesDetailDAO {

    @Override
    public boolean save(String suppliesId, List<SuppliesDetail> suppliesDetailList) throws SQLException {
        for (SuppliesDetail suppliesDetail : suppliesDetailList){
            if (!save(suppliesId,suppliesDetail)){
                return false;
            }
        }
        return true;
    }

    public boolean save(String suppliesId, SuppliesDetail suppliesDetail) throws SQLException {
        String sql = "INSERT INTO supplies_detail (Supplies_Id,Mattirial_Code,Mattirial_Qty,Unit_Price,Total) VALUES (?,?,?,?,?)";
        return CrudUtil.execute(sql,suppliesId,suppliesDetail.getMaterialCode(),suppliesDetail.getMaterialQty(),suppliesDetail.getMaterialCost(),suppliesDetail.getTotal());
    }
}
