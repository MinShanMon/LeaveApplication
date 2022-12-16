package ca.team3.laps.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LeaveHistoryDisplay {

    
    private Integer id;
    
    private String type;

    private LocalDate startDate;

    private LocalDate endDate;

    private int period;

    private LeaveStatusEnum status;
    
    private String reason;

    private String work;

}
