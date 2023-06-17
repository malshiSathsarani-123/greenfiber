package lk.ijse.greenfiber.bo.custom;

import lk.ijse.greenfiber.bo.SuperBO;
import lk.ijse.greenfiber.dto.ProductionDetailDTO;
import lk.ijse.greenfiber.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProductBO extends SuperBO {

    boolean deleteProduct(String id) throws SQLException;

    boolean updateProduct(ProductDTO d) throws SQLException;

    ProductDTO searchProduct(String id) throws SQLException;

    boolean saveProductionDetail(ProductDTO product, String code, ArrayList<ProductionDetailDTO> manufacturingDto1) throws SQLException;

    List<ProductDTO> getAllProduct() throws SQLException;

}
