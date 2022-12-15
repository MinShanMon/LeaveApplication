package ca.team3.laps.service;

import java.util.List;
import java.util.Objects;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.team3.laps.model.Leave;
import ca.team3.laps.model.LeaveHistoryDisplay;
import ca.team3.laps.model.CalendarificAPI.Holiday;
import ca.team3.laps.repository.CalendarRepo;
import ca.team3.laps.repository.LeaveRepository;
import ca.team3.laps.repository.StaffRepo;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    LeaveRepository leaveRepository;

    @Autowired
    StaffRepo staffRepo;

    @Autowired
    CalendarRepo calendarRepo;

    @Override
    public List<LeaveHistoryDisplay> leaveHistory(long staffid) {
        return leaveRepository.findByStaffid(staffid);
    }

    @Override
    public LeaveHistoryDisplay updateLeaveHistory(String id, LeaveHistoryDisplay leaves) {
        // TODO Auto-generated method stub
        Leave leave = leaveRepository.findById(id).get();
        leave.setStartDate(leaves.getStartDate());
        leave.setEndDate(leaves.getEndDate());

        LocalDate date1 = leaves.getStartDate();
			LocalDate date2 = leaves.getEndDate();
			List<Holiday> dates = calendarRepo.findByYear(date1.getYear());
			int count = 0;
			for(int i = 0; i< dates.size(); i++ ){
				if((dates.get(i).getDate().isEqual(date1) || dates.get(i).getDate().isAfter(date1)) && (dates.get(i).getDate().isEqual(date2) || dates.get(i).getDate().isBefore(date2))){
					count++;
				}
			}
            int peri= leaves.getEndDate().getDayOfYear()- leaves.getStartDate().getDayOfYear();
        leave.setPeriod(peri-count);
        leave.setType(leaves.getType());
        leaveRepository.save(leave);
        
        return leaveRepository.findByLeaveid(id);
    }

    
    @Override
    public Leave getwithId(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    // @Override
    // public Leave updateLeave(Leave leave, String leaveId) {
    //     Leave leave1 = leaveRepository.findById(leaveId).get();

    //     if(leave != null){
            
    //     }
    //     return null;
    // }

    
}

