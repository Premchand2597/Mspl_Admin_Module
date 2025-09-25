package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.GroupMailId_Entity;

import jakarta.transaction.Transactional;
	
@Repository
public interface GroupMailId_Repo extends JpaRepository<GroupMailId_Entity, Long>{
	
	@Query(nativeQuery = true, value = "Select * from group_mail_id_table")
	List<GroupMailId_Entity> getGroupMailIdData();
	
//	@Query(nativeQuery = true, value = "select * from group_mail_id_table where id=:auto_id")
//	GroupMailId_Entity editGroupHolidayData(@Param("auto_id") String auto_id);
	
	@Query(nativeQuery = true, value = "select * from group_mail_id_table where id=cast(:auto_id as integer)")
	GroupMailId_Entity editGroupHolidayData(@Param("auto_id") String auto_id);
	
	@Modifying
 	@Transactional
	@Query(nativeQuery = true, value="update group_mail_id_table set main_mail_id=:main_mail_id, sub_mail_id=:sub_mail_id, group_mail_id=:group_mail_id where id=:id")
	void update_GroupMailId_details(@Param("id") long id, @Param("main_mail_id") String main_mail_id, @Param("sub_mail_id") String sub_mail_id, 
			@Param("group_mail_id") String group_mail_id);

	@Modifying
 	@Transactional
	@Query(nativeQuery = true, value="DELETE FROM group_mail_id_table WHERE id=:id")
	void delete_GroupMailIdDetails(@Param("id") long id);
	
//	@Query(nativeQuery = true, value = "SELECT * FROM group_mail_id_table WHERE FIND_IN_SET(:old_email, group_mail_id)")
//	GroupMailId_Entity fetchParticularGroupMailData(@Param("old_email") String old_email);
	
	@Query(nativeQuery = true, value = """
			
SELECT * FROM group_mail_id_table WHERE :old_email = ANY(string_to_array(group_mail_id, ','));
			
			""")
	GroupMailId_Entity fetchParticularGroupMailData(@Param("old_email") String old_email);
	
//	@Modifying
//	@Query(nativeQuery = true, value = "UPDATE group_mail_id_table SET group_mail_id = REPLACE(group_mail_id, :oldEmail, :newEmail) WHERE FIND_IN_SET(:oldEmail, group_mail_id)")
//	void updateGroupMailId(@Param("oldEmail") String oldEmail, @Param("newEmail") String newEmail);
	
	@Modifying
	@Query(nativeQuery = true, value = """
			
UPDATE group_mail_id_table SET group_mail_id = REPLACE(group_mail_id, :oldEmail, :newEmail) WHERE :oldEmail = ANY(string_to_array(group_mail_id, ','));

			""")
	void updateGroupMailId(@Param("oldEmail") String oldEmail, @Param("newEmail") String newEmail);
}
