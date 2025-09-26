package com.example.mspl_connect.Sales_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Sales_Entity.Terms_and_condition_Entity;


@Repository
public interface Terms_and_condition_Repo extends JpaRepository<Terms_and_condition_Entity, Long>{
	
	@Query(nativeQuery = true, value="select * from terms_and_condition_table where id=1")
	Terms_and_condition_Entity fetchRecentInsertedTermsAndConditionDetail();
	
	@Query(nativeQuery = true, value="select * from terms_and_condition_table where q_id=:qId order by id desc limit 1")
	Terms_and_condition_Entity fetchRecentInsertedTermsAndConditionDetailNew(@Param("qId") String qId);
	
	@Query(nativeQuery = true, value = """
		    SELECT * FROM terms_and_condition_table 
		    WHERE id = (
		        SELECT MAX(id) 
		        FROM terms_and_condition_table 
		        WHERE q_id = :qId
		    )
		""")
		Terms_and_condition_Entity fetchRecentInsertedTermsAndConditionDetail(String qId);

	
}
