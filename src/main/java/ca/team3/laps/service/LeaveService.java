package ca.team3.laps.service;

import java.util.List;

import ca.team3.laps.model.Leave;
import ca.team3.laps.model.LeaveHistoryDisplay;
import ca.team3.laps.model.Staff;

import java.time.LocalDate;
public interface LeaveService {
    List<LeaveHistoryDisplay> leaveHistory(Integer staffid);
    
    // Leave updateLeave(Leave leave, String leaveId);
    LeaveHistoryDisplay updateLeaveHistory(Integer id, LeaveHistoryDisplay leave);

    Leave getwithId(String id);

    LeaveHistoryDisplay createLeaveHistory(Integer id,LeaveHistoryDisplay leave);

    public List<Staff> getStaff();

    Staff getStaffWithStaffId(Integer id);

    List<Staff> getSubordinate(Integer id);
}
