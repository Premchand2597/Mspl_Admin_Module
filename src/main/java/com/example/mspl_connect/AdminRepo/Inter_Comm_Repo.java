package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.InterComm_Entity;

import jakarta.transaction.Transactional;

@Repository
public interface Inter_Comm_Repo extends JpaRepository<InterComm_Entity, Integer>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM inter_comm_details order by username")
	List<InterComm_Entity> getInterCommDetails();
	
//	@Query(nativeQuery = true, value = "select * from inter_comm_details where id=:auto_id")
//	InterComm_Entity editInterCommData(@Param("auto_id") String auto_id);
	
	@Query(nativeQuery = true, value = "select * from inter_comm_details where id=cast(:auto_id as integer)")
	InterComm_Entity editInterCommData(@Param("auto_id") String auto_id);
	
	@Modifying
 	@Transactional
	@Query(nativeQuery = true, value="update inter_comm_details set username=:username, cell_number=:cell_number, mail_id=:mail_id, tele_number=:tele_number, cubical_number=:cubical_number, seat_place=:seat_place, floor_number=:floor_number, room_number=:room_number where id=:id")
	void update_InterComm_details(@Param("id") int id, @Param("username") String username, @Param("cell_number") String cell_number, 
			@Param("mail_id") String mail_id, @Param("tele_number") String tele_number, @Param("cubical_number") String cubical_number, 
			@Param("seat_place") String seat_place, @Param("floor_number") String floor_number, @Param("room_number") String room_number);
	

	@Modifying
 	@Transactional
	@Query(nativeQuery = true, value="DELETE FROM inter_comm_details WHERE id=:id")
	void delete_InterCommDetails(@Param("id") int id);
	
	@Query(nativeQuery = true, value="select mail_id from inter_comm_details where mail_id=:emailId")
    String findEmailExitsOrNot(String emailId);
	
	@Modifying
    @Transactional
    @Query(nativeQuery = true, value="insert into inter_comm_details(username, cell_number, mail_id, tele_number, cubical_number, seat_place, floor_number, room_number) values(:empName, :mobileNo, :email, null, null, null, null, null)")
    void insertIntercommDetailsForNewEmployee(@Param("empName") String empName, @Param("mobileNo") String mobileNo, @Param("email") String email);
	
	@Query(nativeQuery = true, value = "select * from inter_comm_details where mail_id=:email")
	InterComm_Entity fetchInterCommDataBasedOnEmail(@Param("email") String email);
}
