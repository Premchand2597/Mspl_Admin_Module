package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.Scap;


@Repository
public interface ScapRepository extends JpaRepository<Scap, Integer> {


	
    // Sum all scrap quantities
    @Query("SELECT COALESCE(SUM(s.quantity), 0) FROM Scap s")
    int sumQuantity();

}
