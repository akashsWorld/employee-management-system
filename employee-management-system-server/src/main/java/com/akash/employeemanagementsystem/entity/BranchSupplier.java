package com.akash.employeemanagementsystem.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "branch supplier")
public class BranchSupplier {

    @EmbeddedId
    private BranchSupplierCompositeKey branchSupplierCompositeKey;


    @ManyToOne
    @MapsId("branchId")
    @JoinColumn(name = "branch_id",referencedColumnName = "branch_id")
    private Branch branch;

    @Column(name = "supply_type")
    private String supplyType;
}
