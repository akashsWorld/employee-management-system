package com.akash.employeemanagementsystem.response_payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchResponsePayload {
    private String branchId;
    private String branchName;
    private Date managerStartDate;
    private ManagerDetails managerDetails;
}
