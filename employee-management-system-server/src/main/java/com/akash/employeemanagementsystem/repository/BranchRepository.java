package com.akash.employeemanagementsystem.repository;

import com.akash.employeemanagementsystem.entity.Branch;
import com.akash.employeemanagementsystem.entity.Employee;
import com.akash.employeemanagementsystem.repository.custom_query_classes.BranchWithManagerQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch,String> {

    @Query(value =
            "select br.branch_id,\n" +
                    "       br.branch_name,\n" +
                    "       em.emp_id,\n" +
                    "       br.manager_start_date,\n" +
                    "       em.birth_date,\n" +
                    "       em.gender,\n" +
                    "       em.first_name,\n" +
                    "       em.last_name,\n" +
                    "       em.salary\n" +
                    "from branch_table br join employee_table em on\n" +
                    "    br.manager_id = em.emp_id WHERE br.branch_id = :branch_id"
            ,nativeQuery = true)
    BranchWithManagerQuery findBranchWithManager(@Param("branch_id") String branchId);


    @Query(value = "select br.manager_id from branch_table br join employee_table em on br.manager_id = em.emp_id WHERE br\n" +
            "    .branch_id=:branch",nativeQuery = true)
    String isManagerPresent(@Param("branch") String branchId);

    @Query(value = "UPDATE employee_table em SET em.super_id= :empId \n" +
            "WHERE  em.branch_id= :branchId ",nativeQuery = true)
    void changeAllEmployeeSuperId( @Param("empId") String managerId, @Param("branchId") String branchId);

    @Query(value = "UPDATE branch_table br SET br.manager_id= :managerId WHERE br\n" +
            "    .branch_id= :branchId;",nativeQuery = true)
    void changeBranchManagerId( @Param("managerId") String managerId, @Param("branchId") String branchId);

    @Query(value = "UPDATE employee_table em SET em.super_id = null WHERE em.emp_id = em.super_id", nativeQuery = true)
    void changeThePreviousManagerSuperIdNull();


    @Query(value = "select br.branch_id,\n" +
            "       br.branch_name,\n" +
            "       em.emp_id,\n" +
            "       br.manager_start_date,\n" +
            "       em.birth_date,\n" +
            "       em.gender,\n" +
            "       em.first_name,\n" +
            "       em.last_name,\n" +
            "       em.salary\n" +
            "from branch_table br join employee_table em on\n" +
            "    br.manager_id = em.emp_id",nativeQuery = true)
    List<BranchWithManagerQuery> findAllBranchesWithManager();




}
