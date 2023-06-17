package lk.ijse.greenfiber.dao.custom;

import lk.ijse.greenfiber.dao.SuperDAO;
import lk.ijse.greenfiber.dto.ProductionDetailDTO;
import lk.ijse.greenfiber.entity.ProductionDetail;

import java.sql.SQLException;
import java.util.List;

public interface ProductionDetailDAO extends SuperDAO {

    boolean save(List<ProductionDetail> productionDetails) throws SQLException ;

    List<ProductionDetail> getAll(String code) throws SQLException ;

    }
