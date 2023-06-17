package lk.ijse.greenfiber.bo.custom.impl;

import lk.ijse.greenfiber.bo.custom.LoginBO;
import lk.ijse.greenfiber.dao.DAOFactory;
import lk.ijse.greenfiber.dao.custom.UserDAO;
import lk.ijse.greenfiber.dao.custom.impl.UserDAOImpl;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean conform(String name, String password) throws SQLException {
        return userDAO.conform(name,password);
    }
}
