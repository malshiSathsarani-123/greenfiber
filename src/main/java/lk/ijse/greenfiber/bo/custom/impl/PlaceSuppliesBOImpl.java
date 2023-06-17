package lk.ijse.greenfiber.bo.custom.impl;

import lk.ijse.greenfiber.bo.custom.PlaceSuppliesBO;
import lk.ijse.greenfiber.dao.DAOFactory;
import lk.ijse.greenfiber.dao.custom.MaterialDAO;
import lk.ijse.greenfiber.dao.custom.SupplierDAO;
import lk.ijse.greenfiber.dao.custom.SuppliesDAO;
import lk.ijse.greenfiber.dao.custom.SuppliesDetailDAO;
import lk.ijse.greenfiber.dao.custom.impl.MaterialDAOImpl;
import lk.ijse.greenfiber.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.greenfiber.dao.custom.impl.SuppliesDAOImpl;
import lk.ijse.greenfiber.dao.custom.impl.SuppliesDetailDAOImpl;
import lk.ijse.greenfiber.db.DBConnection;
import lk.ijse.greenfiber.dto.MaterialDTO;
import lk.ijse.greenfiber.dto.SuppliesDTO;
import lk.ijse.greenfiber.dto.SuppliesDetailDTO;
import lk.ijse.greenfiber.entity.Material;
import lk.ijse.greenfiber.entity.Supplies;
import lk.ijse.greenfiber.entity.SuppliesDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceSuppliesBOImpl implements PlaceSuppliesBO {

    SuppliesDAO suppliesDAO = (SuppliesDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPLIES);
    MaterialDAO materialDAO = (MaterialDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MATERIAL);
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    SuppliesDetailDAO suppliesDetailDAO = (SuppliesDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIERSDETAIL);

    @Override
    public String generateNextSuppliesId() throws SQLException {
        return suppliesDAO.generateNextSuppliesId();
    }

    @Override
    public List<String> generateMaterialCode() throws SQLException {
        return materialDAO.genarateId();
    }

    @Override
    public List<String> generateSupplierId() throws SQLException {
        return supplierDAO.genarateId();
    }

    @Override
    public MaterialDTO searchMaterial(String code) throws SQLException {
        Material material = materialDAO.search(code);
        return new MaterialDTO(material.getMattirial_Id(),material.getDescription(),material.getQty_on_hand(),material.getUnit_price());
    }

    @Override
    public String getSupplierName(String id) throws SQLException {
        return supplierDAO.getName(id);
    }

    @Override
    public boolean saveSupplies(SuppliesDTO dto, List<SuppliesDetailDTO> suppliesDetailDTOList) throws SQLException {
        List<SuppliesDetail> suppliesDetailList = new ArrayList<>();
        for (SuppliesDetailDTO s : suppliesDetailDTOList) {
            suppliesDetailList.add(new SuppliesDetail(s.getSuppliesId(), s.getMaterialCode(), s.getMaterialQty(), s.getMaterialCost(), s.getTotal()));
        }
        Connection connection = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isSave = suppliesDAO.save(new Supplies(dto.getSuppliesId(), dto.getAmount(), dto.getDate(), dto.getTime(), dto.getSupplierId(), dto.getAmount()));
            if (isSave) {
                boolean isUpdated = materialDAO.updateQtySupplies(suppliesDetailList);
                if (isUpdated) {
                    boolean isSaveSuppliesDetail = suppliesDetailDAO.save(dto.getSuppliesId(), suppliesDetailList);
                    if (isSaveSuppliesDetail) {
                        connection.commit();
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);

        }
    }
}

