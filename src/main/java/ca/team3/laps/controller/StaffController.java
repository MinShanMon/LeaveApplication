package ca.team3.laps.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import ca.team3.laps.model.Staff;
import ca.team3.laps.service.StaffService;
@RestController
@RequestMapping("/api")
public class StaffController {

    @Autowired
    StaffService staffService;
    @PostMapping("/staff/post")
    public @ResponseBody Staff createStaff(Staff staff){
        
        return staffService.createStaff(staff);
    }
}
