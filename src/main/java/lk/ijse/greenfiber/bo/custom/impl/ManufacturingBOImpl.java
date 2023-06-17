package lk.ijse.greenfiber.bo.custom.impl;

import lk.ijse.greenfiber.bo.custom.ManufacturingBO;
import lk.ijse.greenfiber.dao.DAOFactory;
import lk.ijse.greenfiber.dao.custom.MaterialDAO;
import lk.ijse.greenfiber.dao.custom.ProductDAO;
import lk.ijse.greenfiber.dao.custom.ProductionDetailDAO;
import lk.ijse.greenfiber.dao.custom.impl.MaterialDAOImpl;
import lk.ijse.greenfiber.dao.custom.impl.ProductDAOImpl;
import lk.ijse.greenfiber.dao.custom.impl.ProductionDetailDAOImpl;
import lk.ijse.greenfiber.db.DBConnection;
import lk.ijse.greenfiber.dto.ProductDTO;
import lk.ijse.greenfiber.dto.ProductionDetailDTO;
import lk.ijse.greenfiber.entity.Product;
import lk.ijse.greenfiber.entity.ProductionDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManufacturingBOImpl implements ManufacturingBO {

    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    MaterialDAO materialDAO = (MaterialDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MATERIAL);
    ProductionDetailDAO productionDetailDAO = (ProductionDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCTIONDETAIL);

    @Override
    public List<String> getProductCodes() throws SQLException {
        return productDAO.genarateId();
    }

    @Override
    public boolean updateManufacturing(String pCode, String pQty, List<ProductionDetailDTO> productionDetailDTOS) throws SQLException {
        List<ProductionDetail> productionDetailList= new ArrayList<>();
        for (ProductionDetailDTO p : productionDetailDTOS) {
            productionDetailList.add(new ProductionDetail(p.getMaterialCode(),p.getQty()));
        }
        Connection connection=null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isUpdate = materialDAO.updateQty(productionDetailList);
            if (isUpdate){
                boolean isUpdateProduct = productDAO.updateQty(pCode,pQty);
                if (isUpdateProduct){
                    connection.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public List<ProductionDetailDTO> getMaterialDetails(String code) throws SQLException {
        List<ProductionDetailDTO> productionDetailDTOS= new ArrayList<>();
        List<ProductionDetail> productionDetails = productionDetailDAO.getAll(code);
        for (ProductionDetail p : productionDetails) {
            productionDetailDTOS.add(new ProductionDetailDTO(p.getMaterial_Code(),p.getMaterial_Qty()));
        }
        return productionDetailDTOS;
    }

    @Override
    public ProductDTO getDescriptionAndCost(String code) throws SQLException {
        Product product = productDAO.getDescription(code);
        return new ProductDTO(product.getDescription(),product.getCost());
    }
}
