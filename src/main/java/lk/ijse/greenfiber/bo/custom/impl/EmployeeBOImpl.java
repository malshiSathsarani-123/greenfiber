package lk.ijse.greenfiber.bo.custom.impl;

import lk.ijse.greenfiber.bo.custom.EmployeeBO;
import lk.ijse.greenfiber.dao.DAOFactory;
import lk.ijse.greenfiber.dao.custom.EmployeeDAO;
import lk.ijse.greenfiber.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.greenfiber.dto.EmployeeDTO;
import lk.ijse.greenfiber.entity.Customer;
import lk.ijse.greenfiber.entity.Employee;
import lk.ijse.greenfiber.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
          public boolean saveEmployee(EmployeeDTO dto) throws SQLException {
            return employeeDAO.save(new Employee(dto.getEmployee_Id(),dto.getFirst_Name(),dto.getLast_Name(),dto.getAddress(),dto.getNic(),dto.getContact(),dto.getRole(),dto.getGender()));
        }

    @Override
        public boolean deleteEmployee(String id) throws SQLException {
            return employeeDAO.delete(id);
        }

    @Override
        public boolean updateEmployee(EmployeeDTO dto) throws SQLException {
            return employeeDAO.update(new Employee(dto.getEmployee_Id(),dto.getFirst_Name(),dto.getLast_Name(),dto.getAddress(),dto.getNic(),dto.getContact(),dto.getRole(),dto.getGender()));
        }

    @Override
        public EmployeeDTO searchNic(String nic) throws SQLException {
            Employee employee = employeeDAO.searchNic(nic);
            return new EmployeeDTO(employee.getEmployee_Id(),employee.getFirst_Name(),employee.getLast_Name(),employee.getAddress(),employee.getNic(),employee.getContact(),employee.getRole(),employee.getGender());
        }

    @Override
        public EmployeeDTO searchEmployee(String id) throws SQLException {
            Employee employee = employeeDAO.search(id);
            return new EmployeeDTO(employee.getEmployee_Id(),employee.getFirst_Name(),employee.getLast_Name(),employee.getAddress(),employee.getNic(),employee.getContact(),employee.getRole(),employee.getGender());
        }

    @Override
    public List<EmployeeDTO> getAllEmployee() throws SQLException {
        List<Employee> employees = employeeDAO.getAll();
        List<EmployeeDTO> employeeDTOS =new ArrayList<>();

        for (Employee employee :employees){
            employeeDTOS.add(new EmployeeDTO(employee.getEmployee_Id(),employee.getFirst_Name(),employee.getLast_Name(),employee.getAddress(),employee.getNic(), employee.getContact(), employee.getRole(),employee.getGender()));

        }
        return  employeeDTOS;
    }
}
