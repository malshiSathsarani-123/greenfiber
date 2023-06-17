package lk.ijse.greenfiber.bo.custom;

import lk.ijse.greenfiber.bo.SuperBO;
import lk.ijse.greenfiber.dto.EmployeeDTO;
import lk.ijse.greenfiber.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBO {

    public boolean saveEmployee(EmployeeDTO employee) throws SQLException ;

    public boolean deleteEmployee(String id) throws SQLException ;

    public boolean updateEmployee(EmployeeDTO employee) throws SQLException ;

    public EmployeeDTO searchNic(String nic) throws SQLException ;

    public EmployeeDTO searchEmployee(String id) throws SQLException ;

    List<EmployeeDTO> getAllEmployee() throws SQLException;
}
