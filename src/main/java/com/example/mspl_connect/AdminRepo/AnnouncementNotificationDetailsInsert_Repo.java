package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AnnouncementNotificationDetailsInsert_Entity;


@Repository
public interface AnnouncementNotificationDetailsInsert_Repo extends JpaRepository<AnnouncementNotificationDetailsInsert_Entity, String>{

}
