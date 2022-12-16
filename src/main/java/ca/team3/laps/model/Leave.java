package ca.team3.laps.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Leave_Application")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="leave_id",nullable=false)
    private int id;

    @Column(name="leaveType",nullable = false)
    private String type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private int period;

    @Column(name = "status", columnDefinition = "ENUM('SUBMITTED', 'APPROVED', 'WITHDRAWN', 'UPDATED', 'REJECTED')")
    @Enumerated(EnumType.STRING)
    private LeaveStatusEnum status;

    @Column(name="rejectReason",nullable = true)
    private String reason;

    @Column(name="workDissemination")
    private String work;

    @ManyToOne
    @JoinColumn(name="staff_id", nullable = true)
    private Staff staff;

    public Leave(String type,LocalDate startDate,LocalDate endDate,int period,
        LeaveStatusEnum status,String reason, String work, Staff staff){ 

        this.type=type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.period = period;
        this.status = status;
        this.reason = reason;
        this.work = work;
        this.staff = staff;
    }
}
