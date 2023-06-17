package lk.ijse.greenfiber.bo.custom;

import lk.ijse.greenfiber.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface ManufacturingCostBO extends SuperBO {

    List<String> generateCode() throws SQLException;

    Double getUnitPrice(String mCode) throws SQLException;

    String getName(String code) throws SQLException;
}
