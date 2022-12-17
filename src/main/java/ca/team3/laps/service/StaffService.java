package ca.team3.laps.service;
import ca.team3.laps.model.Staff;
public interface StaffService {
    Staff getStaffWithStaffId(Integer id);
    Staff createStaff(Staff staff);
}
