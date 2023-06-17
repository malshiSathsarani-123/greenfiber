package lk.ijse.greenfiber.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;

@AllArgsConstructor
@Data
public class Attendance {
    private String id;
    private  String name;
    private Time inTime;
    private  Time outTime;
    private  Integer hours;
    private  String type;
    private String date;

    public Attendance(String id, String name, Time inTime, String type, String date) {
        this.id = id;
        this.name = name;
        this.inTime = inTime;
        this.type = type;
        this.date = date;
    }

    public Attendance(String id, Time outTime, Integer hours) {
        this.id = id;
        this.outTime = outTime;
        this.hours = hours;
    }
}
