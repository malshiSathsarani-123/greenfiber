package lk.ijse.greenfiber.bo.custom;

import lk.ijse.greenfiber.bo.SuperBO;
import lk.ijse.greenfiber.dto.ProductDTO;
import lk.ijse.greenfiber.dto.ProductionDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface ManufacturingBO extends SuperBO {

    List<String> getProductCodes() throws SQLException;

    boolean updateManufacturing(String pCode, String pQty, List<ProductionDetailDTO> cartMaterialDtoList) throws SQLException;

    List<ProductionDetailDTO> getMaterialDetails(String code) throws SQLException;

    ProductDTO getDescriptionAndCost(String code) throws SQLException;
}
