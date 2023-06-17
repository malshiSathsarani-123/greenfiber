package lk.ijse.greenfiber.dao.custom;

import lk.ijse.greenfiber.dao.CrudDAO;
import lk.ijse.greenfiber.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO <Customer,String>{

    Customer searchNic(String nic) throws SQLException ;

    String getName(String id) throws SQLException ;

    String getLastCustomer() throws SQLException ;

    Integer getCount() throws SQLException;
}
