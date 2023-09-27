package com.akash.employeemanagementsystem.controller;

import com.akash.employeemanagementsystem.auth_service.JWTService;
import com.akash.employeemanagementsystem.entity.Employee;
import com.akash.employeemanagementsystem.request_payloads.EmployeeAssignToBranch;
import com.akash.employeemanagementsystem.request_payloads.EmployeeCreateRequest;
import com.akash.employeemanagementsystem.response_payload.EmployeeResponsePayload;
import com.akash.employeemanagementsystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/employee")

// TODO:Apply Role based Authentication

public class EmployeeController {
    private final EmployeeService employeeService;
    private final JWTService jwtService;

    @PostMapping(value = "/saveEmployee")
    public ResponseEntity<EmployeeResponsePayload> saveEmployee(@RequestBody EmployeeCreateRequest employeeCreateRequest
                                                                /* ,@RequestHeader("Authorization") String
                                                                requestHeader*/){
//        TODO: ADD authentication
//        if(!requestHeader.startsWith("Bearer ")){
//            throw new RuntimeException("Token Not Valid");
//        }
        return ResponseEntity.ok(employeeService.saveEmployee(employeeCreateRequest,employeeCreateRequest.getEmail()));
    }

    @PutMapping(value = "/joinEmployeeToBranch")
    public ResponseEntity<EmployeeResponsePayload> addManagerToEmployee(@RequestBody EmployeeAssignToBranch employeeManagerCreateRequest){
        return ResponseEntity.ok().body(employeeService.addEmployeeToBranch(employeeManagerCreateRequest));
    }

    @PutMapping(value = "/changeEmployeeBranch")
    public ResponseEntity<EmployeeResponsePayload> changeEmployeeBranch(@RequestBody EmployeeAssignToBranch employeeAssignToBranch){
        return ResponseEntity.ok(employeeService.changeEmployeeBranch(employeeAssignToBranch));
    }

    @DeleteMapping(value = "/deleteEmployee/{id}")
    public ResponseEntity<EmployeeResponsePayload> deleteEmployeeById(@PathVariable(value = "id")String empId){
        return ResponseEntity.ok(employeeService.deleteEmployeeById(empId));
    }



}
