package lk.ijse.greenfiber.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class AttendenceTm {
    private String id;
    private  String name;
    private Time inTime;
    private  String type;
    private String date;
}
