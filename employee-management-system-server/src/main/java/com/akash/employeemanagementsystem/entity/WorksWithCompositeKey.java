package com.akash.employeemanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class WorksWithCompositeKey implements Serializable {

    @Column(name = "emp_id")
    private String employeeId;

    @Column(name = "client_id")
    private String clientId;

}
