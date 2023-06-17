//package lk.ijse.greenfiber.model;
//
//import lk.ijse.greenfiber.dto.User;
//import lk.ijse.greenfiber.util.CrudUtil;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class UserModel {
//    public static boolean save(User user) throws SQLException {
//        boolean isAttend = search(user.getName());
//        if (isAttend){
//            return false;
//        }
//        String sql = "INSERT INTO user (User_Name,Contact,Password,mail) VALUES (?,?,?,?)";
//        return CrudUtil.execute(sql,user.getName(),user.getContact(),user.getPassword(),user.getMail());
//    }
//
//    private static boolean search(String name) throws SQLException {
//        String sql = "SELECT * FROM user WHERE User_Name = ?";
//        ResultSet resultSet = CrudUtil.execute(sql,name);
//
//        if (resultSet.next()){
//            return true;
//        }
//        return false;
//    }
//
//    public static boolean update(User user) throws SQLException {
//        String sql = "UPDATE user SET Contact = ?,Password = ? ,mail = ? WHERE User_Name = ?";
//        return CrudUtil.execute(sql,user.getContact(),user.getPassword(),user.getMail(),user.getName());
//    }
//
//    public static boolean delete(String id) throws SQLException {
//        String sql = "DELETE FROM user WHERE  User_Name = ? ";
//        return CrudUtil.execute(sql,id);
//    }
//
//    public static boolean conform(String name, String password) throws SQLException {
//        String sql = "SELECT * FROM user WHERE User_Name = ? && PassWord = ?";
//        ResultSet resultSet = CrudUtil.execute(sql,name,password);
//
//        if (resultSet.next()){
//            return true;
//        }
//        return false;
//    }
//}
