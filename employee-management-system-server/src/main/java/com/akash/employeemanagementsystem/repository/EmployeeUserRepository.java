package com.akash.employeemanagementsystem.repository;

import com.akash.employeemanagementsystem.auth_entity.EmployeeUser;
import com.akash.employeemanagementsystem.repository.custom_query_classes.GetEmployeeForDeleteCustomQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeUserRepository extends JpaRepository<EmployeeUser,String> {
    Optional<EmployeeUser> findEmployeeUserByEmail(String email);

    @Query(nativeQuery = true, value = "select eu.emp_id,\n" +
            "       et.first_name,\n" +
            "       et.last_name,\n" +
            "       et.gender,\n" +
            "       et.birth_date,\n" +
            "       br.branch_name,\n" +
            "       br.branch_id,\n" +
            "       br.manager_start_date,\n" +
            "       em.emp_id as managerId,\n" +
            "       em.first_name as managerFirstName,\n" +
            "       em.last_name as managerLastName,\n" +
            "       em.birth_date as managerBirthDate,\n" +
            "       em.gender as managerGender\n" +
            "from employee_user_table eu join\n" +
            "    employee_table et on\n" +
            "    eu.emp_id = et.emp_id join\n" +
            "    branch_table br\n" +
            "    on et\n" +
            "    .branch_id = br.branch_id\n" +
            "    join employee_table em on\n" +
            "    br.manager_id= em.emp_id WHERE eu\n" +
            "    .emp_id=:empId")
    GetEmployeeForDeleteCustomQuery getEmployeeForDelete(@Param("empId") String employeeId);
}
