package com.akash.employeemanagementsystem.controller;

import com.akash.employeemanagementsystem.request_payloads.BranchCreateRequest;
import com.akash.employeemanagementsystem.request_payloads.EmployeeBranchUpdateRequest;
import com.akash.employeemanagementsystem.response_payload.BranchResponsePayload;
import com.akash.employeemanagementsystem.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @PutMapping(value = "/addBranchManager")
    public ResponseEntity<BranchResponsePayload> addManagerBranchUpdate(@RequestBody EmployeeBranchUpdateRequest branchUpdateRequest){
        return ResponseEntity.ok().body(branchService.addBranchMangerUpdate(branchUpdateRequest));
    }

    @PostMapping(value = "/addBranch")
    public ResponseEntity<BranchResponsePayload> createBranch(@RequestBody BranchCreateRequest branchCreateRequest){
        return ResponseEntity.ok().body(branchService.createBranch(branchCreateRequest));
    }

    @PutMapping(value = "/changeBranchManager")
    public ResponseEntity<BranchResponsePayload> changeBranchManager(@RequestBody EmployeeBranchUpdateRequest employeeBranchUpdateRequest){
        return ResponseEntity.ok(branchService.changeBranchManager(employeeBranchUpdateRequest));
    }

    @GetMapping(value = "/getAllBranches")
    public ResponseEntity<List<BranchResponsePayload>> getAllBranches(){
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    @GetMapping(value = "/getBranchById/{branchId}")
    public ResponseEntity<BranchResponsePayload> getBranchById(@PathVariable String branchId){
        return ResponseEntity.ok(branchService.getBranchById(branchId));
    }

    @DeleteMapping(value = "/deleteAllBranches")
    public ResponseEntity<List<BranchResponsePayload>> deleteAllBranches(){
        return ResponseEntity.ok(branchService.deleteAllBranches());
    }

    @DeleteMapping(value = "/deleteBranchById/{branchId}")
    public ResponseEntity<BranchResponsePayload> deleteBranchById(@PathVariable String branchId){
        return ResponseEntity.ok(branchService.deleteBranchById(branchId));
    }
}
