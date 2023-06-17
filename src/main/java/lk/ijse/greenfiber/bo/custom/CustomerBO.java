package lk.ijse.greenfiber.bo.custom;

import lk.ijse.greenfiber.bo.SuperBO;
import lk.ijse.greenfiber.dto.CustomerDTO;
import lk.ijse.greenfiber.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {

      boolean saveCustomer(CustomerDTO dto) throws SQLException ;

      CustomerDTO searchCustomer(String id) throws SQLException ;

      CustomerDTO searchNicCustomer(String nic) throws SQLException ;

      boolean updateCustomer(CustomerDTO dto) throws SQLException ;

      String getName(String id) throws SQLException ;

      String getLastCustomer() throws SQLException ;

      boolean deleteCustomer(String id) throws SQLException ;

    List<CustomerDTO> getAllCustomer() throws SQLException;
}
