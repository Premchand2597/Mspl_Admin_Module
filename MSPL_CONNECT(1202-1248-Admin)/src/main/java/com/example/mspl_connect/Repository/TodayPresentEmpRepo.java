package com.example.mspl_connect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.TodayPresentEmpEntity;

@Repository
public interface TodayPresentEmpRepo extends JpaRepository<TodayPresentEmpEntity, String> {
	
}
