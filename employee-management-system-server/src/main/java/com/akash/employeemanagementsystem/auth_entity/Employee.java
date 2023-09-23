package com.akash.employeemanagementsystem.auth_entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Employee {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private LocalDateTime dateTime;
    private Gender gender;
    private Double salary;
    private Employee employee;

}
