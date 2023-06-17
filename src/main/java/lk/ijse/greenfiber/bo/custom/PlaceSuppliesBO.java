package lk.ijse.greenfiber.bo.custom;

import lk.ijse.greenfiber.bo.SuperBO;
import lk.ijse.greenfiber.dto.MaterialDTO;
import lk.ijse.greenfiber.dto.SuppliesDTO;
import lk.ijse.greenfiber.dto.SuppliesDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface PlaceSuppliesBO extends SuperBO {

    String generateNextSuppliesId() throws SQLException;

    List<String> generateMaterialCode() throws SQLException;

    List<String> generateSupplierId() throws SQLException;

    MaterialDTO searchMaterial(String code) throws SQLException;

    String getSupplierName(String id) throws SQLException;

    boolean saveSupplies(SuppliesDTO suppliesDTO, List<SuppliesDetailDTO> suppliesDetailDTOList) throws SQLException;
}
