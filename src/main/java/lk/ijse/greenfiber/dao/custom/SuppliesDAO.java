package lk.ijse.greenfiber.dao.custom;

import lk.ijse.greenfiber.dao.SuperDAO;
import lk.ijse.greenfiber.dto.SuppliesDTO;
import lk.ijse.greenfiber.entity.Supplies;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SuppliesDAO extends SuperDAO {

    public String generateNextSuppliesId() throws SQLException ;

    public Integer getCount() throws SQLException ;

    public List<String> genarateId() throws SQLException ;

    public Double getAmount(String id) throws SQLException ;

    public String getSupplierId(String id) throws SQLException ;

    public boolean updatePayment(String orderId, Double amount) throws SQLException ;

    boolean save(Supplies supplies) throws SQLException;
}
