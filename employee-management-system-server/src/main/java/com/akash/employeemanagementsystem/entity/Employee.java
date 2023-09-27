package com.akash.employeemanagementsystem.entity;

import com.akash.employeemanagementsystem.auth_entity.EmployeeUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

// TODO: Define all the relations between the databases

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="employee_table")
public class Employee {

    @Id
    @Column(name = "emp_id",columnDefinition = "varchar(36)")
    private String employeeId;

    @Column(name = "first_name",columnDefinition = "varchar(20)")
    private String firstName;

    @Column(name = "last_name",columnDefinition = "varchar(20)")
    private String lastName;

    @Column(name = "birth_date",columnDefinition = "DATE")
    private Date birthDate;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "salary")
    private Double salary;

    @OneToOne
    @MapsId
    @JoinColumn(name = "emp_id")
    @JsonIgnore
    private EmployeeUser employeeUser;

    @OneToOne(mappedBy = "employee")
    private Branch branch;


    @OneToMany(mappedBy = "employee")
    private Set<Employee> employees =new HashSet<>();

    @ManyToOne
    @JoinColumn(referencedColumnName = "emp_id",name = "super_id")
    @JsonIgnore
    private Employee employee;


    @OneToOne
    @JsonIgnore
    @JoinColumn(referencedColumnName = "branch_id",name = "branch_id")
    private Branch branchName;


    @OneToMany(mappedBy = "employee")
    private Set<WorksWith> clientSet = new HashSet<>();

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", salary=" + salary +
                ", branch=" + branch +
                '}';
    }
}
