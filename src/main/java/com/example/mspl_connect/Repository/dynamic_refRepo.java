package com.example.mspl_connect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.Task;

import jakarta.transaction.Transactional;

@Repository
public interface dynamic_refRepo extends JpaRepository<Task, Integer> {

    @Modifying
    @Transactional
    @Query(value="INSERT INTO daynamic_ref_table (email_id, task_weight, project_id) VALUES (:email, :task_weight, :prjId)", nativeQuery = true)
    void dynamicRefTable(String email, String task_weight, String prjId);
}
