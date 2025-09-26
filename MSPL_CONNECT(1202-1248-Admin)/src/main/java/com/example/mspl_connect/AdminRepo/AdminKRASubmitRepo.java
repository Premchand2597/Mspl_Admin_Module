package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.ApprisalForAdmin;


@Repository
public interface AdminKRASubmitRepo extends JpaRepository<ApprisalForAdmin, Integer> {

}
