package com.akash.employeemanagementsystem.response_payload;


import com.akash.employeemanagementsystem.entity.Client;
import com.akash.employeemanagementsystem.entity.Gender;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class EmployeeResponsePayload {

    private String empId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Gender gender;
    private Double salary;
    private BranchResponsePayload branch;
    private List<Client> clients;
    private Double totalSales;
}
