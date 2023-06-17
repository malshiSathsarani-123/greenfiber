package lk.ijse.greenfiber.dao.custom.impl;

import lk.ijse.greenfiber.dao.custom.AttendenceDAO;
import lk.ijse.greenfiber.entity.Attendance;
import lk.ijse.greenfiber.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendenceDAOImpl implements AttendenceDAO {

    @Override
    public boolean save(List<Attendance> dtoList) throws SQLException {
        for (Attendance attendance : dtoList){
            if (!save(attendance)){
                return false;
            }
        }
        return true;
    }

    private boolean save(Attendance attendance) throws SQLException {
        if (attendance.getType().equals("Present")){
            String sql = "INSERT INTO attendence (Employee_Id,Name,Date,In_Time,Type) VALUES (?,?,?,?,?)";
            return CrudUtil.execute(sql,attendance.getId(),attendance.getName(),attendance.getDate(),attendance.getInTime(),attendance.getType());
        }
        String sql = "INSERT INTO attendence (Employee_Id,Name,Date,Type) VALUES (?,?,?,?)";
        return CrudUtil.execute(sql,attendance.getId(),attendance.getName(),attendance.getDate(),attendance.getType());

    }

    @Override
    public List<Attendance> getAll(LocalDate dates) throws SQLException {
        String sql = "SELECT * FROM attendence WHERE DATE = ? && Type = ? ";
        ResultSet resultSet = CrudUtil.execute(sql, dates,"Present");

        List<Attendance> attendanceList = new ArrayList<>();

        while (resultSet.next()){
            String id= resultSet.getString(1);
            String name = resultSet.getString(2);
            Time inTime = resultSet.getTime(3);
            Time outTime = resultSet.getTime(4);
            Integer i =    resultSet.getInt(5);
            String type =    resultSet.getString(6);
            String date = resultSet.getString(7);

            attendanceList.add(new Attendance(id,name,inTime,outTime,i,type,date));
        }
        return attendanceList;
    }

    @Override
    public List<Attendance> getAllA(String dates) throws SQLException {
        String sql = "SELECT * FROM attendence WHERE DATE = ?";
        ResultSet resultSet = CrudUtil.execute(sql, dates);

        List<Attendance> attendanceList = new ArrayList<>();

        while (resultSet.next()){
            String id= resultSet.getString(1);
            String name = resultSet.getString(2);
            Time inTime = resultSet.getTime(3);
            Time outTime = resultSet.getTime(4);
            Integer i =    resultSet.getInt(5);
            String type =    resultSet.getString(6);
            String date = resultSet.getString(7);

            attendanceList.add(new Attendance(id,name,inTime,outTime,i,type,date));
        }
        return attendanceList;
    }

    @Override
    public boolean update(List<Attendance> attendanceList) throws SQLException {
        for (Attendance attendance : attendanceList){
            if (!update(attendance)) {
                return false;
            }
        }
        return true;
    }

    private boolean update(Attendance attendance) throws SQLException {
        String sql = "UPDATE attendence SET Out_Time = ?,Working_hours = ?  WHERE Employee_Id = ?";
        return CrudUtil.execute(sql,attendance.getOutTime(),attendance.getHours(),attendance.getId());
    }

    @Override
    public boolean search(String id, String date) throws SQLException {
        String sql = "SELECT * FROM attendence WHERE Employee_Id = ? && Date = ?";
        ResultSet resultSet = CrudUtil.execute(sql,id,date);
        if (resultSet.next()){
            return true;
        }
        return false;
    }

    @Override
    public void getTime(Time outTime, Time inTime) throws SQLException {
        String sql = " SELECT TIMEDIFF(?, ?) as time_diff";
        ResultSet resultSet = CrudUtil.execute(sql,outTime,inTime);

        if (resultSet.next()){
            System.out.println("gkkk");
        }
    }

    @Override
    public Integer getCount() throws SQLException {
        String sql = "SELECT COUNT(Employee_Id) FROM attendence WHERE Date = ? && Type = ?";
        ResultSet resultSet = CrudUtil.execute(sql,LocalDate.now(),"Present");
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public Integer getAbsentCount() throws SQLException {
        String sql = "SELECT COUNT(Employee_Id) FROM attendence WHERE Date = ? && Type = ?";
        ResultSet resultSet = CrudUtil.execute(sql,LocalDate.now(),"Absent");
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public List<Attendance> getAll(String ids) throws SQLException {
        String sql = "SELECT * FROM attendence WHERE Employee_Id = ?";
        ResultSet resultSet = CrudUtil.execute(sql, ids);

        List<Attendance> attendanceList = new ArrayList<>();

        while (resultSet.next()){
            String id= resultSet.getString(1);
            String name = resultSet.getString(2);
            Time inTime = resultSet.getTime(3);
            Time outTime = resultSet.getTime(4);
            Integer i =    resultSet.getInt(5);
            String type =    resultSet.getString(6);
            String date = resultSet.getString(7);

            attendanceList.add(new Attendance(id,name,inTime,outTime,i,type,date));
        }
        return attendanceList;
    }
}
