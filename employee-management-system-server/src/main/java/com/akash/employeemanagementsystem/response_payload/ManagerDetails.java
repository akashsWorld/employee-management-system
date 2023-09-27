package com.akash.employeemanagementsystem.response_payload;

import com.akash.employeemanagementsystem.entity.Gender;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerDetails {
    private String managerId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Gender gender;
}
