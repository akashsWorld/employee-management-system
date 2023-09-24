package com.akash.employeemanagementsystem.service;

import com.akash.employeemanagementsystem.entity.Employee;
import com.akash.employeemanagementsystem.entity.EmployeeRequest;

public interface EmployeeService {

    Employee saveEmployee(EmployeeRequest employeeRequest, String userName);
}
