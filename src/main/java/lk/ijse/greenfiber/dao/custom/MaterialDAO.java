package lk.ijse.greenfiber.dao.custom;

import lk.ijse.greenfiber.dao.CrudDAO;
import lk.ijse.greenfiber.dto.SuppliesDetailDTO;
import lk.ijse.greenfiber.dto.SuppliesDTO;
import lk.ijse.greenfiber.entity.Material;
import lk.ijse.greenfiber.entity.ProductionDetail;
import lk.ijse.greenfiber.entity.SuppliesDetail;

import java.sql.SQLException;
import java.util.List;

public interface MaterialDAO extends CrudDAO<Material,String> {

    public String getName(String code) throws SQLException ;

    public Double getUnitPrice(String mCode) throws SQLException ;

    public boolean updateQty(List<ProductionDetail> productionDetailList) throws SQLException ;

    public boolean updateQtySupplies(List<SuppliesDetail> suppliesDetailList) throws SQLException ;

    public boolean updateQtySupplies1(SuppliesDetail suppliesDetail) throws SQLException ;

    public Material getLastMattirialData() throws SQLException ;

}
