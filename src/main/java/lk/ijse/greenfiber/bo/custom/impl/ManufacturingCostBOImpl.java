package lk.ijse.greenfiber.bo.custom.impl;

import lk.ijse.greenfiber.bo.custom.ManufacturingCostBO;
import lk.ijse.greenfiber.dao.DAOFactory;
import lk.ijse.greenfiber.dao.custom.MaterialDAO;
import lk.ijse.greenfiber.dao.custom.impl.MaterialDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class ManufacturingCostBOImpl implements ManufacturingCostBO {

    MaterialDAO materialDAO = (MaterialDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MATERIAL);

    @Override
    public List<String> generateCode() throws SQLException {
        return materialDAO.genarateId();
    }

    @Override
    public Double getUnitPrice(String mCode) throws SQLException {
        return materialDAO.getUnitPrice(mCode);
    }

    @Override
    public String getName(String code) throws SQLException {
        return materialDAO.getName(code);
    }
}
