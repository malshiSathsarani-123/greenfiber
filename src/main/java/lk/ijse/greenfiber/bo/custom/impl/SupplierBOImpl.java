package lk.ijse.greenfiber.bo.custom.impl;

import lk.ijse.greenfiber.bo.custom.SupplierBO;
import lk.ijse.greenfiber.dao.DAOFactory;
import lk.ijse.greenfiber.dao.custom.SupplierDAO;
import lk.ijse.greenfiber.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.greenfiber.dto.SupplierDTO;
import lk.ijse.greenfiber.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public boolean deleteSupplier(String id) throws SQLException {
        return supplierDAO.delete(id);
    }

    @Override
    public boolean saveSupplier(SupplierDTO dto) throws SQLException{
        return supplierDAO.save(new Supplier(dto.getSupplier_Id(),dto.getFirst_Name(),dto.getLast_Name(),dto.getCompany_Name(),dto.getNic(),dto.getContact()));
    }

    @Override
    public boolean updateSupplier(SupplierDTO dto) throws SQLException{
        return supplierDAO.update(new Supplier(dto.getSupplier_Id(),dto.getFirst_Name(),dto.getLast_Name(),dto.getCompany_Name(),dto.getNic(),dto.getContact()));
    }

    @Override
    public SupplierDTO searchSupplier(String id) throws SQLException{
        Supplier supplier = supplierDAO.search(id);
        return new SupplierDTO(supplier.getSupplier_Id(),supplier.getFirst_Name(),supplier.getLast_Name(),supplier.getCompany_Name(),supplier.getNic(),supplier.getContact());
    }

    @Override
    public SupplierDTO searchNic(String nic) throws SQLException{
        Supplier supplier = supplierDAO.searchNic(nic);
        return new SupplierDTO(supplier.getSupplier_Id(),supplier.getFirst_Name(),supplier.getLast_Name(),supplier.getCompany_Name(),supplier.getNic(),supplier.getContact());
    }

    @Override
    public String getLastSupplirId() throws SQLException {
        return supplierDAO.getLastSupplirId();
    }

    @Override
    public String getName(String id) throws SQLException {
        return supplierDAO.getName(id);
    }

    @Override
    public List<SupplierDTO> getAllSuplier() throws SQLException {
        List<Supplier> suppliers =supplierDAO.getAll();
        List<SupplierDTO> supplierDTOS =new ArrayList<>();

        for (Supplier supplier :suppliers){
            supplierDTOS.add(new SupplierDTO(supplier.getSupplier_Id(),supplier.getFirst_Name(),supplier.getFirst_Name(),supplier.getCompany_Name(),supplier.getNic(),supplier.getContact()));
        }
        return supplierDTOS;
    }
}
