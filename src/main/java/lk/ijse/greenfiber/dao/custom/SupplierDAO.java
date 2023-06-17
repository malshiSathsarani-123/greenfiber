package lk.ijse.greenfiber.dao.custom;

import lk.ijse.greenfiber.dao.CrudDAO;
import lk.ijse.greenfiber.entity.Supplier;

import java.sql.SQLException;

public interface SupplierDAO extends CrudDAO<Supplier,String> {

    public Supplier searchNic(String nic) throws SQLException ;

    public String getName(String code) throws SQLException ;

    public String getLastSupplirId() throws SQLException ;

    public Integer getCount() throws SQLException ;
}
