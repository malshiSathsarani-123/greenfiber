package lk.ijse.greenfiber.dao.custom.impl;

import lk.ijse.greenfiber.dao.custom.MaterialDAO;
import lk.ijse.greenfiber.entity.Material;
import lk.ijse.greenfiber.entity.ProductionDetail;
import lk.ijse.greenfiber.entity.SuppliesDetail;
import lk.ijse.greenfiber.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAOImpl implements MaterialDAO {
    @Override
    public boolean save(Material matirial) throws SQLException {
        try {
            Material matirial1 = search(matirial.getMattirial_Id());
            if (matirial1 != null) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "INSERT INTO mattirial (Mattirial_Code, Description, Qty_On_Hand, Unit_Price) VALUES (?,?,?,?)";
        return CrudUtil.execute(sql,matirial.getMattirial_Id(),matirial.getDescription(),matirial.getQty_on_hand(),matirial.getUnit_price());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM mattirial WHERE  Mattirial_Code = ? ";
        return  CrudUtil.execute(sql, id);

    }

    @Override
    public Material search(String id) throws SQLException {
        String sql = "SELECT * FROM mattirial WHERE Mattirial_Code = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);

        if (resultSet.next()){
            String mId = resultSet.getString(1);
            String description = resultSet.getString(2);
            Integer qty_on_hand = Integer.valueOf(resultSet.getString(3));
            Double unitPrice = Double.valueOf(resultSet.getString(4));

            return new Material(mId,description,qty_on_hand,unitPrice);
        }
        return null;
    }

    @Override
    public boolean update(Material matirial) throws SQLException {
        String sql = "UPDATE mattirial SET Description = ?, Qty_On_Hand = ?, Unit_Price = ? WHERE Mattirial_Code = ?";
        return CrudUtil.execute(sql,matirial.getDescription(),matirial.getQty_on_hand(),matirial.getUnit_price(), matirial.getMattirial_Id());
    }

    @Override
    public List<String> genarateId() throws SQLException {
        String sql = "SELECT Mattirial_Code FROM mattirial";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String>codes = new ArrayList<>();

        while (resultSet.next()){
            codes.add(resultSet.getString(1));
        }
        return codes;
    }

    @Override
    public String getName(String code) throws SQLException {
        String sql = "SELECT description FROM mattirial WHERE Mattirial_Code = ?";
        ResultSet resultSet = CrudUtil.execute(sql,code);

        String name = "";
        if (resultSet.next()){
            name = resultSet.getString(1);
        }
        return name;
    }

    @Override
    public Double getUnitPrice(String mCode) throws SQLException {
        String sql = "SELECT Unit_Price FROM mattirial WHERE Mattirial_Code = ?";
        ResultSet resultSet = CrudUtil.execute(sql,mCode);

        Double unitPrice = 0.0;
        if (resultSet.next()){
            unitPrice = Double.valueOf(resultSet.getString(1));
        }
        return unitPrice;
    }

    @Override
    public boolean updateQty(List<ProductionDetail> productionDetailList) throws SQLException {
        for (ProductionDetail p : productionDetailList){
            if (!updateQty(p)){
                return false;
            }
        }
        return true;
    }

    public boolean updateQty(ProductionDetail p) throws SQLException {
        String sql1 = "SELECT Qty_On_Hand FROM mattirial WHERE Mattirial_Code = ?";
        ResultSet resultSet = CrudUtil.execute(sql1, p.getMaterial_Code());
        if (resultSet.next()) {
            Integer qty = resultSet.getInt(1);
            if (qty > p.getMaterial_Qty()) {
                String sql = "UPDATE mattirial SET Qty_On_Hand = (Qty_On_Hand - ?) WHERE Mattirial_Code = ?";
                return CrudUtil.execute(sql, p.getMaterial_Qty(), p.getMaterial_Code());
            }
        }
        return false;
    }

    @Override
    public boolean updateQtySupplies(List<SuppliesDetail> suppliesDetailList) throws SQLException {
        for (SuppliesDetail suppliesDetail : suppliesDetailList){
            if(!updateQtySupplies1(suppliesDetail)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateQtySupplies1(SuppliesDetail suppliesDetail) throws SQLException {
        String sql = "UPDATE mattirial SET Qty_On_Hand = (Qty_On_Hand + ?) WHERE Mattirial_Code = ?";
        return CrudUtil.execute(sql,suppliesDetail.getMaterialQty(),suppliesDetail.getMaterialCode());
    }

    @Override
    public Material getLastMattirialData() throws SQLException {
        String sql = "SELECT * FROM mattirial ORDER BY Mattirial_Code DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()){
            String code = resultSet.getString(1);
            String description = resultSet.getString(2);
            int qty = resultSet.getInt(3);
            double price = resultSet.getDouble(4);

            return new Material(code,description,qty,price);
        }
        return null;
    }

    @Override
    public List<Material> getAll() throws SQLException {
        String sql = "SELECT * FROM mattirial";
        ResultSet resultSet = CrudUtil.execute(sql);
        List<Material>matirialList = new ArrayList<>();

        while (resultSet.next()){
            matirialList.add(new Material(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            ));
        }
        return matirialList;
    }
}
