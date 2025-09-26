package com.example.mspl_connect.AdminRepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.AppraisalHrEntity;

@Repository
public interface AppraisalHrREpo extends JpaRepository<AppraisalHrEntity, Long> {

	  @Query(nativeQuery = true,value="SELECT * FROM apprisal_hr WHERE emp_id = 'MS122'  AND apprisal_link_flag = true ORDER BY apprisal_submit_date_admin DESC LIMIT 1")
	  Optional<AppraisalHrEntity> findByEmpIdAndApprisalLinkFlag(String empId, Boolean apprisalLinkFlag);
	
}
