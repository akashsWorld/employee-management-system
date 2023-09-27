package com.akash.employeemanagementsystem.repository;

import com.akash.employeemanagementsystem.entity.Employee;
import com.akash.employeemanagementsystem.repository.custom_query_classes.FindEmployeeBySuperId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {


        @Query(value = "SELECT * FROM employee_table where branch_id=:branchId",nativeQuery = true)
        List<Employee>findAllEmployeesWhereBranchIdIsThis(@Param("branchId") String branchId);

        @Query(value = "SELECT em.first_name," +
                "em.last_name," +
                "em.gender," +
                "em.emp_id," +
                "em.birth_date," +
                "em.salary " +
                "FROM employee_table em" +
                " " +
                "where em" +
                ".super_id = " +
                ":superId",
                nativeQuery =
                true)
        List<FindEmployeeBySuperId> findAllEmployeesBySuperId(@Param("superId") String superId);



}
