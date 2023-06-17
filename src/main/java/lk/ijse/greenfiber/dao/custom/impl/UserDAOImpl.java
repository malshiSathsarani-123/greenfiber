package lk.ijse.greenfiber.dao.custom.impl;

import lk.ijse.greenfiber.dao.custom.UserDAO;
import lk.ijse.greenfiber.entity.User;
import lk.ijse.greenfiber.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {


    public boolean save(User user) throws SQLException {
        boolean isAttend = searchUser(user.getName());
        if (isAttend){
            return false;
        }
        String sql = "INSERT INTO user (User_Name,Contact,Password,mail) VALUES (?,?,?,?)";
        return CrudUtil.execute(sql,user.getName(),user.getContact(),user.getPassword(),user.getMail());
    }

    @Override
    public boolean searchUser(String name) throws SQLException {
        String sql = "SELECT * FROM user WHERE User_Name = ?";
        ResultSet resultSet = CrudUtil.execute(sql,name);

        if (resultSet.next()){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(User user) throws SQLException {
        String sql = "UPDATE user SET Contact = ?,Password = ? ,mail = ? WHERE User_Name = ?";
        return CrudUtil.execute(sql,user.getContact(),user.getPassword(),user.getMail(),user.getName());
    }

    @Override
    public List<String> genarateId() throws SQLException {
        return null;
    }

    @Override
    public List<User> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM user WHERE  User_Name = ? ";
        return CrudUtil.execute(sql,id);
    }

    @Override
    public User search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean conform(String name, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE User_Name = ? && PassWord = ?";
        ResultSet resultSet = CrudUtil.execute(sql,name,password);

        if (resultSet.next()){
            return true;
        }
        return false;
    }
}
