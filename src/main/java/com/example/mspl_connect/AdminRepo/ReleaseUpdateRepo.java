package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.ReleaseUpdate;

import jakarta.transaction.Transactional;

@Repository
public interface ReleaseUpdateRepo extends JpaRepository<ReleaseUpdate, Integer> {
	
	@Modifying
    @Transactional
	@Query(nativeQuery = true, value = "update release_update set is_read = 0 where empid=:empId")
	void updateReleaseNoteFlag(String empId);

}
