package com.akash.employeemanagementsystem.controller;

import com.akash.employeemanagementsystem.auth_service.JWTService;
import com.akash.employeemanagementsystem.entity.Employee;
import com.akash.employeemanagementsystem.entity.EmployeeRequest;
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
    public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeRequest employeeRequest,
                                                 @RequestHeader("Authorization") String requestHeader){

        if(!requestHeader.startsWith("Bearer ")){
            throw new RuntimeException("Token Not Valid");
        }

        String userName = jwtService.extractUserEmail(requestHeader.substring(7));

        return ResponseEntity.ok(employeeService.saveEmployee(employeeRequest,userName));
    }
}
