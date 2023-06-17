package lk.ijse.greenfiber.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Supplies {
    private String suppliesId;
    private String amount;
    private LocalDate date;
    private Time time;
    private String supplierId;
    private String OutstandingAmount;
}
