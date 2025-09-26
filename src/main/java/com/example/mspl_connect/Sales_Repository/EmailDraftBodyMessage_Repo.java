package com.example.mspl_connect.Sales_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Sales_Entity.EmailDraftBodyMessage_Entity;


@Repository
public interface EmailDraftBodyMessage_Repo extends JpaRepository<EmailDraftBodyMessage_Entity, Long>{
	
	@Query(nativeQuery = true, value="select * from email_draft_box_message where id=1")
	EmailDraftBodyMessage_Entity fetchRecentInsertedEmailDraftMessageDetail();
	
}
