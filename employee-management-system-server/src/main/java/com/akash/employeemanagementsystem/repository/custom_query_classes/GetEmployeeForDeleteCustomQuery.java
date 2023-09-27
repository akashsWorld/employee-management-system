package com.akash.employeemanagementsystem.repository.custom_query_classes;

import com.akash.employeemanagementsystem.entity.Gender;

import java.util.Date;

public interface GetEmployeeForDeleteCustomQuery {
    String getEmp_id();
    Double getSalary();

    String getFirst_name();
    String getLast_name();
    Gender getGender();
    Date getBirth_date();

    String getBranch_name();
    String getBranch_id();
    Date getManager_start_date();

    String getManagerId();
    String getManagerFirstName();
    String getManagerLastName();

    Date getManagerBirthDate();
    Gender getMangerGender();

}
