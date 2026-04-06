package com.Finance.Dashboard.Service;


import com.Finance.Dashboard.DTO.DashboardSummary;
import com.Finance.Dashboard.Exception.AccessDeniedException;
import com.Finance.Dashboard.Model.Enum.RecordType;
import com.Finance.Dashboard.Model.Enum.Role;
import com.Finance.Dashboard.Model.FinRecords;
import com.Finance.Dashboard.Repository.FinRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    public FinRecordsRepository finRecordsRepository;



    public DashboardSummary getDashboradSummary(Role role) {
 if(role != Role.ADMIN && role != Role.ANALYST && role != Role.VIEWER){
   throw new AccessDeniedException("You don't have permission to access this");
 }
        List<FinRecords> recordsList = finRecordsRepository.findAll();
        double totalIncome = 0;
        double totalExpense = 0;

      for(FinRecords record : recordsList){
          if(record.getType() == RecordType.INCOME){
              totalIncome += record.getAmount();
          }
          if(record.getType() == RecordType.EXPENSE){
              totalExpense += record.getAmount();
          }
        }
      double balance = totalIncome - totalExpense;
      DashboardSummary dashboardSummary = new DashboardSummary();
      dashboardSummary.setTotalExpense(totalExpense);
        dashboardSummary.setTotalIncome(totalIncome);
        dashboardSummary.setBalance(balance);

        return dashboardSummary;
    }
}
