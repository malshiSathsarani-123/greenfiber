package lk.ijse.greenfiber.bo.custom.impl;

import lk.ijse.greenfiber.bo.custom.UserBO;
import lk.ijse.greenfiber.dao.DAOFactory;
import lk.ijse.greenfiber.dao.custom.UserDAO;
import lk.ijse.greenfiber.dao.custom.impl.UserDAOImpl;
import lk.ijse.greenfiber.dto.UserDTO;
import lk.ijse.greenfiber.entity.User;

import java.sql.SQLException;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean delete(String id) throws SQLException {
        return userDAO.delete(id);
    }

    @Override
    public boolean save(UserDTO dto) throws SQLException {
        return userDAO.save(new User(dto.getName(),dto.getContact(),dto.getPassword(),dto.getMail()));
    }

    @Override
    public boolean update(UserDTO dto) throws SQLException {
        return userDAO.update(new User(dto.getName(),dto.getContact(),dto.getPassword(),dto.getMail()));
    }
}
