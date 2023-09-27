package com.akash.employeemanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "branch_table")
public class Branch {



    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "branch_id")
    private String branchId;

    @Column(name = "manager_start_date")
    private Date managerStartDate;

    @Column(name = "branch_name")
    private String branchName;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "manager_id",referencedColumnName = "emp_id")
    private Employee employee;

    @OneToOne(mappedBy = "branchName")
    private Employee employeeName;

    @OneToMany(mappedBy = "branch")
    private Set<Client> clients=new HashSet<>();


    @OneToMany(mappedBy = "branch")
    private List<BranchSupplier> branchSuppliers;



    @Override
    public String toString() {
        return "Branch{" +
                "branchId='" + branchId + '\'' +
                ", managerStartDate=" + managerStartDate +
                ", branchName='" + branchName + '\'' +
                '}';
    }

}
