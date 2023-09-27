package com.akash.employeemanagementsystem.repository;

import com.akash.employeemanagementsystem.entity.BranchSupplier;
import com.akash.employeemanagementsystem.entity.BranchSupplierCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchSupplierRepository extends JpaRepository<BranchSupplier, BranchSupplierCompositeKey> {
}
