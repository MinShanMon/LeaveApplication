package ca.team3.laps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import ca.team3.laps.model.Leave;
import ca.team3.laps.model.Staff;
import ca.team3.laps.repository.StaffRepo;

public class StaffServiceImpl implements StaffService{

    @Autowired
    StaffRepo staffRepo;
    @Override
    public List<Leave> getLeaveByStaffId(Integer id) {
        Staff staff = staffRepo.findById(id).get();
        List<Leave> leaves = staff.getLeave();
        return leaves;
    }
    
}
