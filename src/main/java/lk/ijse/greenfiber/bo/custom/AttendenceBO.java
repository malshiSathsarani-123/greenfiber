package lk.ijse.greenfiber.bo.custom;

import lk.ijse.greenfiber.bo.SuperBO;
import lk.ijse.greenfiber.dto.AttendanceDTO;
import lk.ijse.greenfiber.entity.Attendance;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface AttendenceBO extends SuperBO {

    public List<String> genarateEmployeeIds() throws SQLException ;

    public String getEmployeeName(String id) throws SQLException ;

    public boolean searchAttend(String id, String date) throws SQLException ;

    public boolean save(List<AttendanceDTO> dtoList) throws SQLException ;

    public List<AttendanceDTO> getAll(LocalDate date) throws SQLException;

    public boolean update(List<AttendanceDTO> dtoList) throws SQLException ;

    List<AttendanceDTO> getAllAttendanceDetails(String date) throws SQLException;

    List<AttendanceDTO> getAllDetails(String id) throws SQLException;
}
