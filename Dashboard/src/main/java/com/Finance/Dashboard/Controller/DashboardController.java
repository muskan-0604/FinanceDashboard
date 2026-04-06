package com.Finance.Dashboard.Controller;

import com.Finance.Dashboard.DTO.DashboardSummary;
import com.Finance.Dashboard.Model.Enum.Role;
import com.Finance.Dashboard.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/summary")
  public  ResponseEntity<DashboardSummary> getSummary(Role role){
        DashboardSummary dashboardSummary = dashboardService.getDashboradSummary(role);
    return new ResponseEntity<>(dashboardSummary, HttpStatus.OK);
    }
}
