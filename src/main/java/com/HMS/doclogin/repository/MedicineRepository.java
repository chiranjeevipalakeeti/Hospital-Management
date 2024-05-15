package com.HMS.doclogin.repository;

import com.HMS.doclogin.entity.Medicines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicines,Long> {
}
