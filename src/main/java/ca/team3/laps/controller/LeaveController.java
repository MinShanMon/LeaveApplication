package ca.team3.laps.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.team3.laps.model.Leave;
import ca.team3.laps.model.LeaveHistoryDisplay;
import ca.team3.laps.service.LeaveService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/leaveHistory")
public class LeaveController {
    @Autowired
    private LeaveService leaveService;

    @GetMapping(value = "/gets/{id}", produces = "application/json")
    public @ResponseBody List<LeaveHistoryDisplay> getHistory(@PathVariable("id") long id){
        List<LeaveHistoryDisplay> Leaves = new ArrayList<LeaveHistoryDisplay>();
        Leaves = leaveService.leaveHistory(id);
        return Leaves;
    }

    @PutMapping(value="/post")
    public @ResponseBody LeaveHistoryDisplay updateLeaveHistory(@RequestBody LeaveHistoryDisplay leave){
        return leaveService.updateLeaveHistory(leave.getId(), leave);    
    }
}
