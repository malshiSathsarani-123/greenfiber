package lk.ijse.greenfiber.bo.custom.impl;

import lk.ijse.greenfiber.bo.custom.MaterialBO;
import lk.ijse.greenfiber.dao.DAOFactory;
import lk.ijse.greenfiber.dao.custom.MaterialDAO;
import lk.ijse.greenfiber.dao.custom.impl.MaterialDAOImpl;
import lk.ijse.greenfiber.dto.MaterialDTO;
import lk.ijse.greenfiber.entity.Material;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialBOImpl implements MaterialBO {

    MaterialDAO materialDAO = (MaterialDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MATERIAL);

    @Override
    public boolean updateMaterial(MaterialDTO dto) throws SQLException {
        return materialDAO.update(new Material(dto.getMattirial_Id(),dto.getDescription(),dto.getQty_on_hand(),dto.getUnit_price()));
    }

    @Override
    public boolean deleteMaterial(String id) throws SQLException {
        return materialDAO.delete(id);
    }

    @Override
    public boolean saveMaterial(MaterialDTO dto) throws SQLException {
       return materialDAO.save(new Material(dto.getMattirial_Id(),dto.getDescription(),dto.getQty_on_hand(),dto.getUnit_price()));
    }

    @Override
    public MaterialDTO searchMaterial(String id) throws SQLException  {
        Material material = materialDAO.search(id);
        return new MaterialDTO(material.getMattirial_Id(),material.getDescription(),material.getQty_on_hand(),material.getUnit_price());
    }

    @Override
    public MaterialDTO getLastMattirial() throws SQLException {
        Material material = materialDAO.getLastMattirialData();
        return new MaterialDTO(material.getMattirial_Id(),material.getDescription(),material.getQty_on_hand(),material.getUnit_price());
    }

    @Override
    public List<MaterialDTO> getAllMaterial() throws SQLException {
        List<Material>materials =materialDAO.getAll();
        List<MaterialDTO> materialDTOS =new ArrayList<>();

        for (Material material :materials){
            materialDTOS.add(new MaterialDTO(material.getMattirial_Id(),material.getDescription(),material.getQty_on_hand(),material.getUnit_price()));

        }
        return materialDTOS;
    }
}
