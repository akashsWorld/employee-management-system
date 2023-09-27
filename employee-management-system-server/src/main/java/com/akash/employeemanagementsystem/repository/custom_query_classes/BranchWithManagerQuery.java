package com.akash.employeemanagementsystem.repository.custom_query_classes;

import com.akash.employeemanagementsystem.entity.Gender;

import java.util.Date;

public interface BranchWithManagerQuery {
    String getEmp_id();
    String getBranch_id();
    Date getManager_start_date();
    Date getBirth_date();

    Gender getGender();
    String getFirst_name();
    String getLast_name();
    String getBranch_name();

    Double getSalary();

}
