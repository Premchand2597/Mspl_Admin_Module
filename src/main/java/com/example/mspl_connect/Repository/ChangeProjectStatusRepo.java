package com.example.mspl_connect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.ProjectEntity;

import jakarta.transaction.Transactional;

@Repository
public interface ChangeProjectStatusRepo extends JpaRepository<ProjectEntity, Integer>{

	@Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update team_project set status= :status ,  completed_date=curdate() where id= :id")
    public int changeLeaveApproveStatus(@Param("id") String id,@Param("status") String status);
}
