package lk.ijse.greenfiber.bo.custom;

import lk.ijse.greenfiber.bo.SuperBO;
import lk.ijse.greenfiber.dto.MaterialDTO;

import java.sql.SQLException;
import java.util.List;

public interface MaterialBO extends SuperBO {

    boolean updateMaterial(MaterialDTO dto) throws SQLException;

    boolean deleteMaterial(String id) throws SQLException;

    boolean saveMaterial(MaterialDTO materialDTO) throws SQLException;

    MaterialDTO searchMaterial(String id) throws SQLException;

    MaterialDTO getLastMattirial() throws SQLException;

    List<MaterialDTO> getAllMaterial() throws SQLException;
}
