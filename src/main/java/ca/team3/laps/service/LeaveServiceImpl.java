package ca.team3.laps.service;

import java.util.List;
import java.util.Objects;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.team3.laps.model.Leave;
import ca.team3.laps.model.LeaveHistoryDisplay;
import ca.team3.laps.model.Staff;
import ca.team3.laps.model.CalendarificAPI.Holiday;
import ca.team3.laps.repository.CalendarRepo;
import ca.team3.laps.repository.LeaveRepository;
import ca.team3.laps.repository.StaffRepo;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    LeaveRepository leaveRepository;

    @Autowired
    StaffRepo staffRepo;

    @Autowired
    CalendarRepo calendarRepo;

    @Override
    public List<LeaveHistoryDisplay> leaveHistory(Integer staffid) {
        return leaveRepository.findByStaffid(staffid);
    }

    @Override
    public LeaveHistoryDisplay updateLeaveHistory(Integer id, LeaveHistoryDisplay leaves) {
        // TODO Auto-generated method stub
        Leave leave = leaveRepository.findById(id).get();
        leave.setStartDate(leaves.getStartDate());
        leave.setEndDate(leaves.getEndDate());

        LocalDate date1 = leaves.getStartDate();
			LocalDate date2 = leaves.getEndDate();

			List<Holiday> dates = calendarRepo.findByYear(date1.getYear());
            if(date1.getYear() != date2.getYear()){
            List<Holiday> dates1 = calendarRepo.findByYear(date2.getYear());
                for(Holiday h: dates1){
                    dates.add(h);
                }
            }
            
			int count = 0;
			for(int i = 0; i< dates.size(); i++ ){
				if((dates.get(i).getDate().isEqual(date1) || dates.get(i).getDate().isAfter(date1)) && (dates.get(i).getDate().isEqual(date2) || dates.get(i).getDate().isBefore(date2))){
					count++;
				}
                
			}

            
            int peri= (int)(leaves.getEndDate().toEpochDay()- leaves.getStartDate().toEpochDay());
            Staff staff = leave.getStaff();
            
        leave.setPeriod(peri-count);
        leave.setType(leaves.getType());
        leaveRepository.save(leave);
        
        return leaveRepository.findByLeaveid(id);
    }

    public List<Staff> getStaff(){
        return staffRepo.findAll();
    }

    @Override
    public List<Staff> getSubordinate(Integer id){
        return staffRepo.findSubordinates(id);
    }
    @Override
    public Staff getStaffWithStaffId(Integer id){
        return staffRepo.findById(id).orElse(null);
    }

    @Override
    public Leave getwithId(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LeaveHistoryDisplay createLeaveHistory(Integer id, LeaveHistoryDisplay leaves) {
        Staff staff = staffRepo.findById(id).get();
        Leave leaveHistory = new Leave();
        leaveHistory.setStartDate(leaves.getStartDate());
        leaveHistory.setEndDate(leaves.getEndDate());
        leaveHistory.setStaff(staff);
        LocalDate date1 = leaves.getStartDate();
			LocalDate date2 = leaves.getEndDate();

			List<Holiday> dates = calendarRepo.findByYear(date1.getYear());
            if(date1.getYear() != date2.getYear()){
            List<Holiday> dates1 = calendarRepo.findByYear(date2.getYear());
                for(Holiday h: dates1){
                    dates.add(h);
                }
            }
            
			int count = 0;
			for(int i = 0; i< dates.size(); i++ ){
				if((dates.get(i).getDate().isEqual(date1) || dates.get(i).getDate().isAfter(date1)) && (dates.get(i).getDate().isEqual(date2) || dates.get(i).getDate().isBefore(date2))){
					count++;
				}
                
			}

            
            int peri= (int)(leaves.getEndDate().toEpochDay()- leaves.getStartDate().toEpochDay());
            
        leaveHistory.setPeriod(peri-count);
        leaveHistory.setReason(leaves.getReason());
        leaveHistory.setType(leaves.getType());
        leaveHistory.setWork(leaves.getWork());
        leaveHistory.setStatus(leaves.getStatus());
        leaveRepository.save(leaveHistory);
        return null;
    }



    
}

