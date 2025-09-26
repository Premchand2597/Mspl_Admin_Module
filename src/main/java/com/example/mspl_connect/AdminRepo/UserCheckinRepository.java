package com.example.mspl_connect.AdminRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.UserCheckin;


@Repository
public interface UserCheckinRepository extends JpaRepository<UserCheckin, Long>  {
	
	 List<UserCheckin> findByEmpIdAndCheckinDate(String empId, String checkinDate);
	 
	 //Optional<UserCheckin> findByEmpIdAndCheckinDate1(String empId, String checkinDate);
	 Optional<UserCheckin> findFirstByEmpIdAndCheckinDate(String empId, String checkinDate);
	 
}
