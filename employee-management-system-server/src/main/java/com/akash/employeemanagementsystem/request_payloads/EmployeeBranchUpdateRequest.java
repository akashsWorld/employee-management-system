package com.akash.employeemanagementsystem.request_payloads;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EmployeeBranchUpdateRequest {
    private String branchId;
    private String managerId;
}
