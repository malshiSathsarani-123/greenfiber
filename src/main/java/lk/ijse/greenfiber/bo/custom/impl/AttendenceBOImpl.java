package lk.ijse.greenfiber.bo.custom.impl;

import lk.ijse.greenfiber.bo.custom.AttendenceBO;
import lk.ijse.greenfiber.dao.DAOFactory;
import lk.ijse.greenfiber.dao.custom.AttendenceDAO;
import lk.ijse.greenfiber.dao.custom.EmployeeDAO;
import lk.ijse.greenfiber.dao.custom.impl.AttendenceDAOImpl;
import lk.ijse.greenfiber.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.greenfiber.dto.AttendanceDTO;
import lk.ijse.greenfiber.entity.Attendance;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendenceBOImpl implements AttendenceBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    AttendenceDAO attendenceDAO = (AttendenceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ATTENDANCE);

    @Override
    public List<String> genarateEmployeeIds() throws SQLException {
        return employeeDAO.genarateIds();
    }

    @Override
    public String getEmployeeName(String id) throws SQLException {
        return employeeDAO.getName(id);
    }

    @Override
    public boolean searchAttend(String id, String date) throws SQLException {
        return attendenceDAO.search(id,date);
    }

    @Override
    public boolean save(List<AttendanceDTO> dtoList) throws SQLException {
        List<Attendance>attendanceList = new ArrayList<>();
        for (AttendanceDTO attendanceDTO : dtoList){
            attendanceList.add(new Attendance(attendanceDTO.getId(),attendanceDTO.getName(),attendanceDTO.getInTime(),attendanceDTO.getType(),attendanceDTO.getDate()));
        }
        return attendenceDAO.save(attendanceList);
    }

    @Override
    public List<AttendanceDTO> getAll(LocalDate date) throws SQLException{
        List<AttendanceDTO>attendanceDTOS = new ArrayList<>();
        List<Attendance>attendanceList = attendenceDAO.getAll(date);
        for (Attendance attendance : attendanceList){
            attendanceDTOS.add(new AttendanceDTO(attendance.getId(),attendance.getName(),attendance.getInTime(),attendance.getType(),attendance.getDate()));
        }
        return attendanceDTOS;
    }

    @Override
    public boolean update(List<AttendanceDTO> dtoList) throws SQLException {
        List<Attendance>attendanceList = new ArrayList<>();
        for (AttendanceDTO attendanceDTO : dtoList){
            attendanceList.add(new Attendance(attendanceDTO.getId(),attendanceDTO.getOutTime(),attendanceDTO.getHours()));
        }
        return attendenceDAO.update(attendanceList);
    }

    @Override
    public List<AttendanceDTO> getAllAttendanceDetails(String date) throws SQLException {
        List<AttendanceDTO>attendanceDTOS = new ArrayList<>();
        List<Attendance>attendanceList = attendenceDAO.getAllA(date);
        for (Attendance attendance : attendanceList){
            attendanceDTOS.add(new AttendanceDTO(attendance.getId(),attendance.getName(),attendance.getInTime(),attendance.getType(),attendance.getDate()));
        }
        return attendanceDTOS;
    }

    @Override
    public List<AttendanceDTO> getAllDetails(String id) throws SQLException {
        List<AttendanceDTO>attendanceDTOS = new ArrayList<>();
        List<Attendance>attendanceList = attendenceDAO.getAll(id);
        for (Attendance attendance : attendanceList){
            attendanceDTOS.add(new AttendanceDTO(attendance.getId(),attendance.getName(),attendance.getInTime(),attendance.getType(),attendance.getDate()));
        }
        return attendanceDTOS;
    }
}
