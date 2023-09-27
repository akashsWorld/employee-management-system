package com.akash.employeemanagementsystem.repository;

import com.akash.employeemanagementsystem.entity.WorksWith;
import com.akash.employeemanagementsystem.entity.WorksWithCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorksWithRepository extends JpaRepository<WorksWith, WorksWithCompositeKey> {
}
