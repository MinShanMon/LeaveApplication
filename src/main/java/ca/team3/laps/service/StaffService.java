package ca.team3.laps.service;

import java.util.List;
import java.util.Optional;

import ca.team3.laps.model.Leave;

public interface StaffService {
    List<Leave> getLeaveByStaffId(Integer id);
}
