package lk.ijse.greenfiber.bo.custom;

import lk.ijse.greenfiber.bo.SuperBO;
import lk.ijse.greenfiber.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBO {

    boolean deleteSupplier(String id) throws SQLException;

    boolean saveSupplier(SupplierDTO supplier) throws SQLException;

    boolean updateSupplier(SupplierDTO supplier) throws SQLException;

    SupplierDTO searchSupplier(String id) throws SQLException;

    SupplierDTO searchNic(String nic) throws SQLException;

    String getLastSupplirId() throws SQLException;

    String getName(String id) throws SQLException;

    List<SupplierDTO> getAllSuplier() throws SQLException;
}
