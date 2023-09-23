package com.akash.employeemanagementsystem.repository;

import com.akash.employeemanagementsystem.auth_entity.EmployeeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeUserRepository extends JpaRepository<EmployeeUser,String> {
    Optional<EmployeeUser> findEmployeeUserByEmail(String email);
}
