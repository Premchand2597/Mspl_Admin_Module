package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.annnouncementNotificationEntity;

import jakarta.transaction.Transactional;

@Repository
public interface AnnnouncementNotificationRepo extends JpaRepository<annnouncementNotificationEntity, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE announcement_notifications SET flag = 0 WHERE emp_id = :empId")
    void changeAnnouncementFlagValueByEmpId(String empId);
}
