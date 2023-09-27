package com.akash.employeemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "client_table")
public class Client {
    @Id
    @Column(name = "client_id")
    private String id;

    @Column(name = "client_name")
    private String clientName;


    @ManyToOne
    @JoinColumn(name = "branch_id",referencedColumnName = "branch_id")
    private Branch branch;

    @OneToMany(mappedBy = "client")
    private Set<WorksWith> employeeSet= new HashSet<>();
}
