package com.akash.employeemanagementsystem.service;

import com.akash.employeemanagementsystem.repository.BranchRepository;
import lombok.RequiredArgsConstructor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//@RequiredArgsConstructor
public class ExternalServices {

//    private final BranchRepository branchRepository;

    public Date dateConvertor(String dateString) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date =null;
        try {
            date= sdf.parse(dateString);
        }catch (ParseException parseException){
            System.out.println(parseException.getMessage());
        }

        return date;
    }

//    public void changeBranchManager(String branchId,String managerId){
//
//        branchRepository.changeAllEmployeeSuperId(managerId,branchId);
//        branchRepository.changeBranchManagerId(managerId,branchId);
//        branchRepository.changeThePreviousManagerSuperIdNull();
//    }


}
