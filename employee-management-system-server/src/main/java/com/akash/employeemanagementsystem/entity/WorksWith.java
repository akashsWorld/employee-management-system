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
@Table(name = "works_with")
public class WorksWith {


    @EmbeddedId
    private WorksWithCompositeKey worksWithCompositeKey;

    @Column(name = "total_sales")
    private Double sales;

    @ManyToOne
    @MapsId(value = "employeeId")
    @JoinColumn(name = "emp_id",referencedColumnName = "emp_id")
    private Employee employee;

    @ManyToOne
    @MapsId(value = "clientId")
    @JoinColumn(name = "client_id",referencedColumnName = "client_id")
    private Client client;

}
