package lk.ijse.greenfiber.dao.custom;

import lk.ijse.greenfiber.dao.CrudDAO;
import lk.ijse.greenfiber.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User,String> {

    public boolean conform(String name, String password) throws SQLException ;

    public boolean searchUser(String name) throws SQLException ;

    }
