package lk.ijse.greenfiber.dao.custom;

import lk.ijse.greenfiber.dao.CrudDAO;
import lk.ijse.greenfiber.dto.EmployeeDTO;
import lk.ijse.greenfiber.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee,String> {

        String getName(String id) throws SQLException ;

        Employee searchNic(String nic) throws SQLException ;

        public List<String> genarateIds() throws SQLException ;
}
