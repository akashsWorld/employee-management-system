package com.akash.employeemanagementsystem.entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRequest {

    private String firstName;
    private String lastName;
    private String gender;
    private Double salary;
}
