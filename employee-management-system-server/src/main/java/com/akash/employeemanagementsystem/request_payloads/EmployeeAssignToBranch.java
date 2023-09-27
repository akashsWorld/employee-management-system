package com.akash.employeemanagementsystem.request_payloads;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAssignToBranch {
    private String employeeId;
    private String branchId;
}
