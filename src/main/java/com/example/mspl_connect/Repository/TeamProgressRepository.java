package com.example.mspl_connect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mspl_connect.Entity.TeamProgress;


public interface TeamProgressRepository extends JpaRepository<TeamProgress, Long> {

    @Query("SELECT tp.email AS email, SUM(tp.percent) AS percent " +
           "FROM TeamProgress tp " +
           "WHERE tp.projectId = :projectId " +
           "GROUP BY tp.email")
    List<TeamProgressSummary> findTeamProgressByProjectId(@Param("projectId") String projectId);
    
    @Query(nativeQuery = true,value="select coalesce(sum(percent),0) from team_progress where project_id=:projectId and task_status='completed'")
    int getProjectCompletedPercent(@Param("projectId") String projectId);
    
    @Query(nativeQuery = true, value = "select id, project_id,date_format(date,'%d-%m-%Y') as date, email, percent, working_progress, flag, task_status, task_name, overdue_flag from team_progress where project_id= :projectId")
    List<TeamProgress> getProjectByprjId(String projectId);
    
}
