package com.akash.employeemanagementsystem.service;

import com.akash.employeemanagementsystem.request_payloads.EmployeeAssignToBranch;
import com.akash.employeemanagementsystem.request_payloads.EmployeeCreateRequest;
import com.akash.employeemanagementsystem.request_payloads.EmployeeUpdateRequest;
import com.akash.employeemanagementsystem.response_payload.EmployeeResponsePayload;

import java.util.List;

public interface EmployeeService {

    EmployeeResponsePayload saveEmployee(EmployeeCreateRequest employeeCreateRequest, String requestHeader);//Working
    EmployeeResponsePayload addEmployeeToBranch(EmployeeAssignToBranch employeeAssignToBranch);//Working

    EmployeeResponsePayload updateEmployee(EmployeeUpdateRequest employeeUpdateRequest);//Not Done

    List<EmployeeResponsePayload> getALlEmployees();//Not Done

    EmployeeResponsePayload geEmployeeByEmployeeId(String empId);//Not Done

    List<EmployeeResponsePayload> deleteALlEmployee();//Not Done

    EmployeeResponsePayload deleteEmployeeById(String empId);//Not working

    EmployeeResponsePayload changeEmployeeBranch(EmployeeAssignToBranch employeeAssignToBranch);//Not Working

}
