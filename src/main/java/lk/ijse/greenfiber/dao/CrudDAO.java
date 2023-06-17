package lk.ijse.greenfiber.dao;

import lk.ijse.greenfiber.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T,ID> extends SuperDAO{

    boolean save(T t) throws SQLException;

    boolean delete(ID id) throws SQLException ;

    T search(String id) throws SQLException ;

    boolean update(T t) throws SQLException ;

    List<ID> genarateId() throws SQLException ;

    List<T> getAll() throws SQLException ;
}
