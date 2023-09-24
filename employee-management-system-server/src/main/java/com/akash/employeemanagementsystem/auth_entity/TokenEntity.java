package com.akash.employeemanagementsystem.auth_entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="token_table")
public class TokenEntity {
    @Id
    @Column(name = "emp_id")
    private String id;

    @Column(name = "token" ,columnDefinition = "varchar(500)")
    private String token;

    @Column(name = "created_at")
    private Date createdAt;


    @OneToOne
    @MapsId
    @JoinColumn(name = "emp_id" )
    @JsonIgnore
    private EmployeeUser employeeUser;
}
