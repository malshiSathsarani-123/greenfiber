package lk.ijse.greenfiber.bo.custom.impl;

import lk.ijse.greenfiber.bo.custom.CustomerBO;
import lk.ijse.greenfiber.dao.DAOFactory;
import lk.ijse.greenfiber.dao.custom.CustomerDAO;
import lk.ijse.greenfiber.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.greenfiber.dto.CustomerDTO;
import lk.ijse.greenfiber.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public  boolean saveCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.save(new Customer(dto.getCustomer_Id(),dto.getFirst_Name(),dto.getLast_Name(),dto.getAddress(),dto.getNic(),dto.getContact(),dto.getGender()));
    }

    @Override
    public  boolean deleteCustomer(String id) throws SQLException {
       return customerDAO.delete(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomer() throws SQLException {
        List<Customer> customers =customerDAO.getAll();
        List<CustomerDTO> customerDTOS =new ArrayList<>();

        for (Customer customer :customers){
            customerDTOS.add(new CustomerDTO(customer.getCustomer_Id(),customer.getFirst_Name(),customer.getLast_Name(),customer.getAddress(),customer.getNic(),customer.getContact(),customer.getGender()));
        }
        return customerDTOS;
    }

    @Override
    public  CustomerDTO searchCustomer(String id) throws SQLException {
        Customer customer = customerDAO.search(id);
        return new CustomerDTO(customer.getCustomer_Id(),customer.getFirst_Name(),customer.getLast_Name(),customer.getAddress(),customer.getNic(),customer.getContact(),customer.getGender());
    }

    @Override
    public  CustomerDTO searchNicCustomer(String nic) throws SQLException {
        Customer customer = customerDAO.searchNic(nic);
        return new CustomerDTO(customer.getCustomer_Id(),customer.getFirst_Name(),customer.getLast_Name(),customer.getAddress(),customer.getNic(),customer.getContact(),customer.getGender());
    }

    @Override
    public  boolean updateCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.update(new Customer(dto.getCustomer_Id(),dto.getFirst_Name(),dto.getLast_Name(),dto.getAddress(),dto.getNic(),dto.getContact(),dto.getGender()));
    }

    @Override
    public  String getName(String id) throws SQLException {
        return customerDAO.getName(id);
    }

    @Override
    public String getLastCustomer() throws SQLException {
        return customerDAO.getLastCustomer();
    }
}
