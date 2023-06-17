package lk.ijse.greenfiber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class SuppliesDTO {
    private String suppliesId;
    private String amount;
    private LocalDate date;
    private Time time;
    private String supplierId;
    private String OutstandingAmount;

    public SuppliesDTO(String suppliesId, String amount, LocalDate date, Time time, String supplierId) {
        this.suppliesId = suppliesId;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.supplierId = supplierId;
    }
}
