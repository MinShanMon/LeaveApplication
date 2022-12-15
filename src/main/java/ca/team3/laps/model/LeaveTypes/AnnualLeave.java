package ca.team3.laps.model.LeaveTypes;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("An_Leave")
@Data
@NoArgsConstructor
public class AnnualLeave extends LeaveType {
    @Column
    private String jobTitle;

    @JsonCreator
    public AnnualLeave(float granularity, float leaveDays, String jobTitle) {
        super(granularity, leaveDays);
        this.jobTitle = jobTitle;
    };
}
