package com.akash.employeemanagementsystem.repository.custom_query_classes;

import com.akash.employeemanagementsystem.entity.Gender;

import java.util.Date;

public interface FindEmployeeBySuperId {
    String getFirst_name();
    String getLast_name();
    Gender getGender();
    String getEmp_id();
    Date getBirth_date();
    Double getSalary();

}
