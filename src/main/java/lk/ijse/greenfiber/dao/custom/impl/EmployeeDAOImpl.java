package lk.ijse.greenfiber.dao.custom.impl;

import lk.ijse.greenfiber.dao.custom.EmployeeDAO;
import lk.ijse.greenfiber.entity.Employee;
import lk.ijse.greenfiber.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public List<String> genarateIds() throws SQLException {
        String sql = "SELECT Employee_Id FROM employee ORDER BY Employee_Id ASC";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String>id=new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }
        return id;
    }
        @Override
        public List<String> genarateId() throws SQLException {
            String sql = "SELECT Employee_Id FROM employee WHERE Role = ? ";
            ResultSet resultSet = CrudUtil.execute(sql,"Drivers");

            List<String>id=new ArrayList<>();

            while (resultSet.next()){
                id.add(resultSet.getString(1));
            }
            return id;
        }

        @Override
        public String getName(String id) throws SQLException {
            String sql = "SELECT First_Name FROM employee WHERE Employee_Id = ? ";
            ResultSet resultSet = CrudUtil.execute(sql,id);

            if (resultSet.next()){
                return resultSet.getString(1);
            }
            return null;
        }

        @Override
        public boolean save(Employee employee) throws SQLException {
//            try {
//                EmployeeDTO employee1 = search(employee.getEmployee_Id());
//                if (employee1 != null) {
//                    return false;
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }

            String sql = "INSERT INTO employee(Employee_Id, First_Name, Last_Name, Address, NIC, Contact, Role, Gender) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            return CrudUtil.execute(sql,employee.getEmployee_Id(),employee.getFirst_Name(),employee.getLast_Name(),employee.getAddress(),employee.getNic(),employee.getContact(), employee.getRole(),employee.getGender());

        }

        @Override
        public boolean delete(String id) throws SQLException {
            String sql = "DELETE FROM employee WHERE Employee_Id = ?";
            return  CrudUtil.execute(sql,id);
        }

        @Override
        public boolean update(Employee employee) throws SQLException {
            String sql ="UPDATE employee SET First_Name = ?, Last_Name = ?, Address = ?, NIC = ?, Contact = ? ,Role = ?, Gender = ? WHERE Employee_Id = ?";
            return CrudUtil.execute(sql,employee.getFirst_Name(),employee.getLast_Name(),employee.getAddress(), employee.getNic(), employee.getContact(),employee.getRole(),employee.getGender(), employee.getEmployee_Id());

        }

        @Override
        public Employee searchNic(String nic) throws SQLException {
            String sql = "SELECT * FROM employee WHERE NIC = ?";

            ResultSet resultSet = CrudUtil.execute(sql,nic);

            if (resultSet.next()){
                String id = resultSet.getString(1);
                String fName = resultSet.getString(2);
                String lName = resultSet.getString(3);
                String address = resultSet.getString(4);
                String cNic = resultSet.getString(5);
                Integer contact = Integer.valueOf(resultSet.getString(6));
                String role = resultSet.getString(7);
                String gender = resultSet.getString(8);

                return new Employee(id, fName, lName, address, cNic, contact, role, gender);
            }
            return null;
        }

        @Override
        public Employee search(String id) throws SQLException {
            String sql = "SELECT * FROM employee WHERE Employee_Id = ?";

            ResultSet resultSet = CrudUtil.execute(sql,id);

            if (resultSet.next()){
                String cId = resultSet.getString(1);
                String fName = resultSet.getString(2);
                String lName = resultSet.getString(3);
                String address = resultSet.getString(4);
                String nic = resultSet.getString(5);
                Integer contact = Integer.valueOf(resultSet.getString(6));
                String role = resultSet.getString(7);
                String gender = resultSet.getString(8);

                return new Employee(cId, fName, lName, address, nic, contact,role,gender);
            }
            return null;
        }



    @Override
        public List<Employee> getAll() throws SQLException {
            String sql = "SELECT * FROM employee";
            ResultSet resultSet = CrudUtil.execute(sql);

            List<Employee>employeeList = new ArrayList<>();

            while (resultSet.next()){
                employeeList.add(new Employee(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getString(8)));
            }
            return employeeList;
        }
}
