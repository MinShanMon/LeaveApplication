package ca.team3.laps.model.LeaveTypes;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance
@DiscriminatorColumn(name = "Leave_Type")
@Data
@NoArgsConstructor
public abstract class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private float granularity;
    private float leaveDays;
    @Column(name = "Leave_Type", nullable = false, updatable = false, insertable = false)
    private String leaveType;

    public LeaveType(float granularity, float leaveDays) {
        this.granularity = granularity;
        this.leaveDays = leaveDays;
    }
}
