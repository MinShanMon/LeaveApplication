package ca.team3.laps.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ca.team3.laps.model.Leave;
import ca.team3.laps.model.LeaveHistoryDisplay;

public interface LeaveRepository extends JpaRepository<Leave, Integer>{
    @Query("SELECT new ca.team3.laps.model.LeaveHistoryDisplay(l.id, l.type, l.startDate, l.endDate, l.period, l.status, l.reason, l.work) FROM Leave l, Staff f WHERE l.staff.stfId= :staffid")
    public List<LeaveHistoryDisplay> findByStaffid(@Param("staffid") Integer staffid);

    @Query("SELECT new ca.team3.laps.model.LeaveHistoryDisplay(l.id, l.type, l.startDate, l.endDate, l.period, l.status, l.reason, l.work) FROM Leave l WHERE l.id= :leaveid")
    public LeaveHistoryDisplay findByLeaveid(@Param("leaveid") Integer leaveid);
}
