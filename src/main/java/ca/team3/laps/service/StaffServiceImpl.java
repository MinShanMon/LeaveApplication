package ca.team3.laps.service;
import ca.team3.laps.model.Staff;
import ca.team3.laps.repository.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {
    
    @Autowired
    StaffRepo staffRepo;

    @Override
    public Staff getStaffWithStaffId(Integer id){
        return staffRepo.findById(id).orElse(null);
    }

    @Override
    public Staff createStaff(Staff staff){
        staffRepo.save(staff);
        return null;
    }

}
