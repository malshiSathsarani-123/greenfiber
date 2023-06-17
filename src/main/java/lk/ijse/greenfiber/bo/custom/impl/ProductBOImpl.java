package lk.ijse.greenfiber.bo.custom.impl;

import lk.ijse.greenfiber.bo.custom.ProductBO;
import lk.ijse.greenfiber.dao.DAOFactory;
import lk.ijse.greenfiber.dao.custom.ProductDAO;
import lk.ijse.greenfiber.dao.custom.ProductionDetailDAO;
import lk.ijse.greenfiber.dao.custom.impl.ProductDAOImpl;
import lk.ijse.greenfiber.dao.custom.impl.ProductionDetailDAOImpl;
import lk.ijse.greenfiber.db.DBConnection;
import lk.ijse.greenfiber.dto.ProductionDetailDTO;
import lk.ijse.greenfiber.dto.ProductDTO;
import lk.ijse.greenfiber.entity.Product;
import lk.ijse.greenfiber.entity.ProductionDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBOImpl implements ProductBO {

    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    ProductionDetailDAO productionDetailDAO = (ProductionDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCTIONDETAIL);

    @Override
    public boolean deleteProduct(String id) throws SQLException {
        return productDAO.delete(id);
    }

    @Override
    public boolean saveProductionDetail(ProductDTO dto, String code, ArrayList<ProductionDetailDTO> productionDetailDTOS) throws SQLException {

        List<ProductionDetail>productionDetails = new ArrayList<>();
        for (ProductionDetailDTO p : productionDetailDTOS){
            productionDetails.add(new ProductionDetail(code,p.getMaterialCode(),p.getQty(),p.getTotal()));
        }

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();

            con.setAutoCommit(false);

            boolean isSave = productDAO.save(new Product(dto.getProductCode(),dto.getDescription(),dto.getQtyOnHand(),dto.getCost(),dto.getUnitPrice()));
            if (isSave) {
                boolean isSave1 = productionDetailDAO.save(productionDetails);
                if (isSave1) {
                    con.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }

    @Override
    public List<ProductDTO> getAllProduct() throws SQLException {
        List<Product> products =productDAO.getAll();
        List<ProductDTO> productDTOS =new ArrayList<>();

        for (Product product :products){
            productDTOS.add(new ProductDTO(product.getProductCode(),product.getDescription(),product.getQtyOnHand(),product.getCost(),product.getUnitPrice()));

        }
        return productDTOS;
    }

    @Override
    public boolean updateProduct(ProductDTO dto) throws SQLException {
        return productDAO.update(new Product(dto.getProductCode(),dto.getDescription(),dto.getQtyOnHand(),dto.getCost(),dto.getUnitPrice()));
    }

    @Override
    public ProductDTO searchProduct(String id) throws SQLException {
        Product product = productDAO.search(id);
        return new ProductDTO(product.getProductCode(),product.getDescription(),product.getQtyOnHand(),product.getCost(),product.getUnitPrice());
    }
}
