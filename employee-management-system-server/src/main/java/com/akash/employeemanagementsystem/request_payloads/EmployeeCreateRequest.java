package com.akash.employeemanagementsystem.request_payloads;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeCreateRequest {

    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private Double salary;
}
