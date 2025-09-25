package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.PendingLeaveWithProfilePicData_Entity;

@Repository
public interface PendingLeaveWithProfilePicData_Repo extends JpaRepository<PendingLeaveWithProfilePicData_Entity, Long>{

	@Query(nativeQuery = true, value = """
			
select la.id, la.empid, la.employee_name, la.from_date, la.to_date, ed.profile_pic_path from leave_application la inner join 
employee_details ed on la.empid=ed.empid where la.way=1;
			
			""")
	List<PendingLeaveWithProfilePicData_Entity> getAllPendingLeavesDetailsWithProfilePics();
}
