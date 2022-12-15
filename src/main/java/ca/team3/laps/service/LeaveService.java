package ca.team3.laps.service;

import java.util.List;

import ca.team3.laps.model.Leave;
import ca.team3.laps.model.LeaveHistoryDisplay;
import java.time.LocalDate;
public interface LeaveService {
    List<LeaveHistoryDisplay> leaveHistory(long staffid);
    
    // Leave updateLeave(Leave leave, String leaveId);
    LeaveHistoryDisplay updateLeaveHistory(String id, LeaveHistoryDisplay leave);

    Leave getwithId(String id);
}
