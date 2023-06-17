package lk.ijse.greenfiber.dao.custom;

import lk.ijse.greenfiber.dao.SuperDAO;
import lk.ijse.greenfiber.entity.SuppliesDetail;

import java.sql.SQLException;
import java.util.List;

public interface SuppliesDetailDAO extends SuperDAO {

    public boolean save(String suppliesId, List<SuppliesDetail> suppliesDetailList) throws SQLException ;
    }
