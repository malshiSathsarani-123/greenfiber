//package lk.ijse.greenfiber.model;
//
//import lk.ijse.greenfiber.dto.Attendance;
//import lk.ijse.greenfiber.dto.AttendenceCartDto;
//import lk.ijse.greenfiber.util.CrudUtil;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Time;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AttencenceModel {
//
//    public static boolean save(List<AttendenceCartDto> dtoList) throws SQLException {
//        for (AttendenceCartDto dto : dtoList){
//            if (!save(dto)){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private static boolean save(AttendenceCartDto dto) throws SQLException {
//        if (dto.getType().equals("Present")){
//            String sql = "INSERT INTO attendence (Employee_Id,Name,Date,In_Time,Type) VALUES (?,?,?,?,?)";
//            return CrudUtil.execute(sql,dto.getId(),dto.getName(),dto.getDate(),dto.getInTime(),dto.getType());
//        }
//        String sql = "INSERT INTO attendence (Employee_Id,Name,Date,Type) VALUES (?,?,?,?)";
//        return CrudUtil.execute(sql,dto.getId(),dto.getName(),dto.getDate(),dto.getType());
//
//    }
//
//    public static List<Attendance> getAll(LocalDate dates) throws SQLException {
//        String sql = "SELECT * FROM attendence WHERE DATE = ? && Type = ? ";
//        ResultSet resultSet = CrudUtil.execute(sql, dates,"Present");
//
//        List<Attendance> attendanceList = new ArrayList<>();
//
//        while (resultSet.next()){
//           String id= resultSet.getString(1);
//           String name = resultSet.getString(2);
//           Time inTime = resultSet.getTime(3);
//           Time outTime = resultSet.getTime(4);
//           Integer i =    resultSet.getInt(5);
//           String type =    resultSet.getString(6);
//           String date = resultSet.getString(7);
//
//            attendanceList.add(new Attendance(id,name,inTime,outTime,i,type,date));
//        }
//        return attendanceList;
//    }
//    public static List<Attendance> getAllA(String dates) throws SQLException {
//        String sql = "SELECT * FROM attendence WHERE DATE = ?";
//        ResultSet resultSet = CrudUtil.execute(sql, dates);
//
//        List<Attendance> attendanceList = new ArrayList<>();
//
//        while (resultSet.next()){
//           String id= resultSet.getString(1);
//           String name = resultSet.getString(2);
//           Time inTime = resultSet.getTime(3);
//           Time outTime = resultSet.getTime(4);
//           Integer i =    resultSet.getInt(5);
//           String type =    resultSet.getString(6);
//           String date = resultSet.getString(7);
//
//            attendanceList.add(new Attendance(id,name,inTime,outTime,i,type,date));
//        }
//        return attendanceList;
//    }
//
//    public static boolean update(List<Attendance> dtoList) throws SQLException {
//
//        for (Attendance dto : dtoList){
//            if (!update(dto)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private static boolean update(Attendance dto) throws SQLException {
//        String sql = "UPDATE attendence SET Out_Time = ?,Working_hours = ?  WHERE Employee_Id = ?";
//        return CrudUtil.execute(sql,dto.getOutTime(),dto.getHours(),dto.getId());
//    }
//
//    public static boolean search(String id, String date) throws SQLException {
//        String sql = "SELECT * FROM attendence WHERE Employee_Id = ? && Date = ?";
//        ResultSet resultSet = CrudUtil.execute(sql,id,date);
//
//        if (resultSet.next()){
//            return true;
//        }
//        return false;
//    }
//
//    public static void getTime(Time outTime, Time inTime) throws SQLException {
//        String sql = " SELECT TIMEDIFF(?, ?) as time_diff";
//        ResultSet resultSet = CrudUtil.execute(sql,outTime,inTime);
//
//        if (resultSet.next()){
//            System.out.println("gkkk");
//        }
//    }
//
//    public static Integer getCount() throws SQLException {
//        String sql = "SELECT COUNT(Employee_Id) FROM attendence WHERE Date = ? && Type = ?";
//        ResultSet resultSet = CrudUtil.execute(sql,LocalDate.now(),"Present");
//        if (resultSet.next()){
//            return resultSet.getInt(1);
//        }
//        return 0;
//    }
//
//    public static Integer getAbsentCount() throws SQLException {
//        String sql = "SELECT COUNT(Employee_Id) FROM attendence WHERE Date = ? && Type = ?";
//        ResultSet resultSet = CrudUtil.execute(sql,LocalDate.now(),"Absent");
//        if (resultSet.next()){
//            return resultSet.getInt(1);
//        }
//        return 0;
//    }
//
//    public static List<Attendance> getAll(String ids) throws SQLException {
//        String sql = "SELECT * FROM attendence WHERE Employee_Id = ?";
//        ResultSet resultSet = CrudUtil.execute(sql, ids);
//
//        List<Attendance> attendanceList = new ArrayList<>();
//
//        while (resultSet.next()){
//            String id= resultSet.getString(1);
//            String name = resultSet.getString(2);
//            Time inTime = resultSet.getTime(3);
//            Time outTime = resultSet.getTime(4);
//            Integer i =    resultSet.getInt(5);
//            String type =    resultSet.getString(6);
//            String date = resultSet.getString(7);
//
//            attendanceList.add(new Attendance(id,name,inTime,outTime,i,type,date));
//        }
//        return attendanceList;
//    }
//}