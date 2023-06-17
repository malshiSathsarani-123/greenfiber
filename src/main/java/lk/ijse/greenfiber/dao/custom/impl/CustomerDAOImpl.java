package lk.ijse.greenfiber.dao.custom.impl;

import lk.ijse.greenfiber.dao.custom.CustomerDAO;
import lk.ijse.greenfiber.entity.Customer;
import lk.ijse.greenfiber.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public  boolean save(Customer customer) throws SQLException {
        try {
            Customer customer1 = search(customer.getCustomer_Id());
            if (customer1 != null) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO customer(Customer_Id, First_Name, Last_Name, Address, Nic, Contact,Gender) VALUES(?, ?, ?, ?, ?, ?, ?)";

        return CrudUtil.execute(sql,customer.getCustomer_Id(),customer.getFirst_Name(),customer.getLast_Name(),customer.getAddress(),customer.getNic(),customer.getContact(),customer.getGender());
    }

    @Override
    public  boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM customer WHERE Customer_Id = ?";

        return CrudUtil.execute(sql,id);
    }

    @Override
    public  Customer search(String id) throws SQLException {
        String sql = "SELECT * FROM customer WHERE Customer_Id = ?";

        ResultSet resultSet = CrudUtil.execute(sql,id);

        if (resultSet.next()){
            String cId = resultSet.getString(1);
            String fName = resultSet.getString(2);
            String lName = resultSet.getString(3);
            String address = resultSet.getString(4);
            String nic = resultSet.getString(5);
            Integer contact = Integer.valueOf(resultSet.getString(6));
            String gender = resultSet.getString(7);

            return new Customer(cId, fName, lName, address, nic, contact,gender);
        }
        return null;
    }

    @Override
    public  Customer searchNic(String nic) throws SQLException {
        String sql = "SELECT * FROM customer WHERE NIC = ?";

        ResultSet resultSet = CrudUtil.execute(sql,nic);

        if (resultSet.next()){
            String id = resultSet.getString(1);
            String fName = resultSet.getString(2);
            String lName = resultSet.getString(3);
            String address = resultSet.getString(4);
            String cNic = resultSet.getString(5);
            Integer contact = Integer.valueOf(resultSet.getString(6));
            String gender = resultSet.getString(7);

            return new Customer(id, fName, lName, address, cNic, contact,gender);
        }
        return null;
    }

    @Override
    public  boolean update(Customer customer) throws SQLException {
        String sql ="UPDATE customer SET First_Name = ?, Last_Name = ?, Address = ?, NIC = ?, Contact = ? ,Gender = ? WHERE Customer_Id = ?";
        return CrudUtil.execute(sql,customer.getFirst_Name(),customer.getLast_Name(),customer.getAddress(), customer.getNic(), customer.getContact(),customer.getGender(), customer.getCustomer_Id());

    }

    @Override
    public  List<String> genarateId() throws SQLException {
        String sql = "SELECT Customer_Id FROM customer ORDER BY Customer_Id ASC";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String>id=new ArrayList<>();

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }
        return id;
    }

    @Override
    public  String getName(String id) throws SQLException {
        String sql = "SELECT First_Name FROM customer WHERE Customer_Id = ?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        String name = "" ;

        if (resultSet.next()){
           name  = resultSet.getString(1);
        }
        return name;
    }

    @Override
    public String getLastCustomer() throws SQLException {
        String sql = "SELECT Customer_Id FROM customer ORDER BY Customer_Id DESC LIMIT 1";

        String id = null;
        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()) {
            id=resultSet.getString(1);
        }
        return id;
    }

    @Override
    public  List<Customer> getAll() throws SQLException {
        String sql = "SELECT * FROM customer";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<Customer>customerList = new ArrayList<>();

        while (resultSet.next()){
            customerList.add(new Customer(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getString(7)));
        }
        return customerList;
    }

    @Override
    public  Integer getCount() throws SQLException {
        String sql = "SELECT COUNT(Customer_Id) FROM customer";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }
}
