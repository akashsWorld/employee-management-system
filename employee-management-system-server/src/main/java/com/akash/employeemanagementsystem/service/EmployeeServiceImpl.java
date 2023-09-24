package com.akash.employeemanagementsystem.service;

import com.akash.employeemanagementsystem.auth_entity.EmployeeUser;
import com.akash.employeemanagementsystem.entity.Employee;
import com.akash.employeemanagementsystem.entity.EmployeeRequest;
import com.akash.employeemanagementsystem.entity.Gender;
import com.akash.employeemanagementsystem.repository.EmployeeRepository;
import com.akash.employeemanagementsystem.repository.EmployeeUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final EmployeeUserRepository employeeUserRepository;
    @Override
    @Transactional
    public Employee saveEmployee(EmployeeRequest employeeRequest, String userName) {

        EmployeeUser employeeUser = employeeUserRepository.findEmployeeUserByEmail(userName)
                .orElseThrow(()->new UsernameNotFoundException("Employee user not found"));

        System.out.println(employeeUser.toString());


        Employee employee =Employee.builder()
                .firstName(employeeRequest.getFirstName())
                .employeeUser(employeeUser)
                .lastName(employeeRequest.getLastName())
                .birthDate(new Date(System.currentTimeMillis()))
                .salary(employeeRequest.getSalary())
                .gender(Gender.MALE)
                .build();

        if(Objects.equals(employeeRequest.getGender(), "female")){
            employee.setGender(Gender.FEMALE);
        } else if (Objects.equals(employeeRequest.getGender(), "other")) {
            employee.setGender(Gender.OTHER);
        }

        return employeeRepository.saveAndFlush(employee);

    }
}
