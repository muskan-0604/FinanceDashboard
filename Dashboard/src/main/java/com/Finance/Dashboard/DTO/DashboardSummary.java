package com.Finance.Dashboard.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DashboardSummary {

    private Double totalIncome;
    private Double totalExpense;
    private Double balance;
}
