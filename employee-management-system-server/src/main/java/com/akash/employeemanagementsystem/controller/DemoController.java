package com.akash.employeemanagementsystem.controller;

import com.akash.employeemanagementsystem.entity.Employee;
import com.akash.employeemanagementsystem.repository.EmployeeRepository;
import com.akash.employeemanagementsystem.request_payloads.BranchCreateRequest;
import com.akash.employeemanagementsystem.request_payloads.EmployeeBranchUpdateRequest;
import com.akash.employeemanagementsystem.request_payloads.EmployeeAssignToBranch;
import com.akash.employeemanagementsystem.response_payload.BranchResponsePayload;
import com.akash.employeemanagementsystem.response_payload.EmployeeResponsePayload;
import com.akash.employeemanagementsystem.service.BranchService;
import com.akash.employeemanagementsystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/demo")
@RestController
@RequiredArgsConstructor
public class DemoController {
    @GetMapping
    public String demoController(){
        return "Hello this is a secured point";
    }

}
