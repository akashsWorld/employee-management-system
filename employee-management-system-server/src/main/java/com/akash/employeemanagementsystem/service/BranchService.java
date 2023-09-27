package com.akash.employeemanagementsystem.service;

import com.akash.employeemanagementsystem.request_payloads.BranchCreateRequest;
import com.akash.employeemanagementsystem.request_payloads.EmployeeBranchUpdateRequest;
import com.akash.employeemanagementsystem.response_payload.BranchResponsePayload;

import java.util.List;

public interface BranchService {

    BranchResponsePayload createBranch(BranchCreateRequest branchCreateRequest);//Working
    BranchResponsePayload addBranchMangerUpdate(EmployeeBranchUpdateRequest branchUpdateRequest);//Working

    BranchResponsePayload changeBranchManager(EmployeeBranchUpdateRequest branchUpdateRequest);//Working but in case
    // of manager not Working

    List<BranchResponsePayload> getAllBranches();//Only get the Branches which have a manager.

    BranchResponsePayload getBranchById(String branchId);//working

    List<BranchResponsePayload> deleteAllBranches();//Not Working

    BranchResponsePayload deleteBranchById(String branchId);//Not Working

}
