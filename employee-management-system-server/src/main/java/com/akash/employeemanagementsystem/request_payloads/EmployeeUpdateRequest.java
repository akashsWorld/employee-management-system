package com.akash.employeemanagementsystem.request_payloads;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeUpdateRequest {
    private String employeeId;
    private String salary;
    private String employeeBranchId;
}
