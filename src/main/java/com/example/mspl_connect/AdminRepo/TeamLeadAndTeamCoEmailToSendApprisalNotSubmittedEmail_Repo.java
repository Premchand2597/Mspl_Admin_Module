package com.example.mspl_connect.AdminRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.TeamLeadAndTeamCoEmailToSendApprisalNotSubmittedEmail_Entity;


@Repository
public interface TeamLeadAndTeamCoEmailToSendApprisalNotSubmittedEmail_Repo extends JpaRepository<TeamLeadAndTeamCoEmailToSendApprisalNotSubmittedEmail_Entity, Long>{

	@Query(nativeQuery = true, value="select id, team_lead_name as team_lead_id, team_co_name as team_co_id, NULL AS team_lead_email, NULL AS team_co_email, NULL AS employee_email from employee_details where empid = :empId and email = :employeeEmail")
	TeamLeadAndTeamCoEmailToSendApprisalNotSubmittedEmail_Entity fetchTeamLeadAndTeamCoEmpId(@Param("empId") String empId, @Param("employeeEmail") String employeeEmail);

	@Query(nativeQuery = true, value="select email as team_lead_email from employee_details where empid=:empId")
	String fetchTeamLeadEmail(@Param("empId") String empId);

	@Query(nativeQuery = true, value="select email as team_co_email from employee_details where empid=:empId")
	String fetchTeamCoEmail(@Param("empId") String empId);
}
