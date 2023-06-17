package lk.ijse.greenfiber.dao.custom;

import lk.ijse.greenfiber.dao.SuperDAO;
import lk.ijse.greenfiber.entity.Attendance;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public interface AttendenceDAO extends SuperDAO {

    public boolean save(List<Attendance> dtoList) throws SQLException ;

    public List<Attendance> getAll(LocalDate dates) throws SQLException ;

    public List<Attendance> getAllA(String dates) throws SQLException ;

    public boolean update(List<Attendance> attendanceList) throws SQLException ;

    public boolean search(String id, String date) throws SQLException ;

    public void getTime(Time outTime, Time inTime) throws SQLException ;

    public Integer getCount() throws SQLException ;

    public Integer getAbsentCount() throws SQLException ;

    public List<Attendance> getAll(String ids) throws SQLException ;
}
