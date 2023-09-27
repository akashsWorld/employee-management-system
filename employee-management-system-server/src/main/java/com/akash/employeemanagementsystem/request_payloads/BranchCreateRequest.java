package com.akash.employeemanagementsystem.request_payloads;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchCreateRequest {
    private String branchName;
}
