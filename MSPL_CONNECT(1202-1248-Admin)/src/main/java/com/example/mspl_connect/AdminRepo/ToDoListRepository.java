package com.example.mspl_connect.AdminRepo;

import java.lang.annotation.Native;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.ToDoList;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Long>{
	 
	 List<ToDoList> findByCompleted(boolean completed);
	 List<ToDoList> findAllByEmpId(String empId);
	 
	 @Query(value = "SELECT COUNT(*) FROM to_do_list t WHERE t.deadlinedate = CURDATE() AND t.deadlinetime BETWEEN DATE_ADD(CURTIME(), INTERVAL 5 MINUTE) AND DATE_ADD(CURTIME(), INTERVAL 10 MINUTE) AND t.completed = false AND t.emp_id = :empId", nativeQuery = true)
     int findTasksByUpcomingDeadline(@Param("empId") String empId);

	 @Query(value="select count(*) from feature_updates where empid=:empid and is_read=1",nativeQuery = true)
	 Integer getFeatureUpdateFlagCountByEmpid(String empid);
	 
	 @Query(value="select COALESCE(sum(case when is_read = 1 then 1 else 0 end), 0) as version_count_flag from release_update where empid = :empID order by id desc limit 1",nativeQuery = true)
	 Integer getNewFeatureNotification(String empID);
	 
}
