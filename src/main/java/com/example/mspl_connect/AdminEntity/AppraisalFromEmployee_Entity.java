package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AppraisalFromEmployee_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String project_score; 
	private String attendance_score;
	private String team_collaboration_score;
	private String communication_score;
	private String initiative_score;
	private String leadership_score;
	private String extra_curricular_score;
	private String total_score;
	private String project_comments;
	private String attendance_comments;
	private String team_collaboration_comments;
	private String communication_comments;
	private String initiative_comments;
	private String leadership_comments;
	private String extra_curricular_comments;
	private String emp_id;
	private String quarter;
	private String quarter_year;
	private String apprisal_admin_flag;
	private String department;
	
	private String user_id;
	private String user_project_score;
	private String user_attendance_score;
	private String user_team_collaboration_score;
	private String user_communication_score;
	private String user_initiative_score;
	private String user_leadership_score;
	private String user_extra_curricular_score;
	private String user_total_score;
	
	private String emp_project_comment;
	private String emp_attendance_comment;
	private String emp_team_collaberation_comment;
	private String emp_communication_comment;
	private String emp_initiative_comment;
	private String emp_leadership_comment;
	private String emp_extra_curr_comment;
	
	private String demerit_point;
	private String demerit_comment;
	
	public AppraisalFromEmployee_Entity(long id, String project_score, String attendance_score,
			String team_collaboration_score, String communication_score, String initiative_score,
			String leadership_score, String extra_curricular_score, String total_score, String project_comments,
			String attendance_comments, String team_collaboration_comments, String communication_comments,
			String initiative_comments, String leadership_comments, String extra_curricular_comments, String emp_id,
			String quarter, String quarter_year, String apprisal_admin_flag, String department, String user_id,
			String user_project_score, String user_attendance_score, String user_team_collaboration_score,
			String user_communication_score, String user_initiative_score, String user_leadership_score,
			String user_extra_curricular_score, String user_total_score, String emp_project_comment,
			String emp_attendance_comment, String emp_team_collaberation_comment, String emp_communication_comment,
			String emp_initiative_comment, String emp_leadership_comment, String emp_extra_curr_comment) {
		super();
		this.id = id;
		this.project_score = project_score;
		this.attendance_score = attendance_score;
		this.team_collaboration_score = team_collaboration_score;
		this.communication_score = communication_score;
		this.initiative_score = initiative_score;
		this.leadership_score = leadership_score;
		this.extra_curricular_score = extra_curricular_score;
		this.total_score = total_score;
		this.project_comments = project_comments;
		this.attendance_comments = attendance_comments;
		this.team_collaboration_comments = team_collaboration_comments;
		this.communication_comments = communication_comments;
		this.initiative_comments = initiative_comments;
		this.leadership_comments = leadership_comments;
		this.extra_curricular_comments = extra_curricular_comments;
		this.emp_id = emp_id;
		this.quarter = quarter;
		this.quarter_year = quarter_year;
		this.apprisal_admin_flag = apprisal_admin_flag;
		this.department = department;
		this.user_id = user_id;
		this.user_project_score = user_project_score;
		this.user_attendance_score = user_attendance_score;
		this.user_team_collaboration_score = user_team_collaboration_score;
		this.user_communication_score = user_communication_score;
		this.user_initiative_score = user_initiative_score;
		this.user_leadership_score = user_leadership_score;
		this.user_extra_curricular_score = user_extra_curricular_score;
		this.user_total_score = user_total_score;
		this.emp_project_comment = emp_project_comment;
		this.emp_attendance_comment = emp_attendance_comment;
		this.emp_team_collaberation_comment = emp_team_collaberation_comment;
		this.emp_communication_comment = emp_communication_comment;
		this.emp_initiative_comment = emp_initiative_comment;
		this.emp_leadership_comment = emp_leadership_comment;
		this.emp_extra_curr_comment = emp_extra_curr_comment;
	}
	
	public String getDemerit_point() {
		return demerit_point;
	}
	public void setDemerit_point(String demerit_point) {
		this.demerit_point = demerit_point;
	}
	public String getDemerit_comment() {
		return demerit_comment;
	}
	public void setDemerit_comment(String demerit_comment) {
		this.demerit_comment = demerit_comment;
	}
	public AppraisalFromEmployee_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProject_score() {
		return project_score;
	}
	public void setProject_score(String project_score) {
		this.project_score = project_score;
	}
	public String getAttendance_score() {
		return attendance_score;
	}
	public void setAttendance_score(String attendance_score) {
		this.attendance_score = attendance_score;
	}
	public String getTeam_collaboration_score() {
		return team_collaboration_score;
	}
	public void setTeam_collaboration_score(String team_collaboration_score) {
		this.team_collaboration_score = team_collaboration_score;
	}
	public String getCommunication_score() {
		return communication_score;
	}
	public void setCommunication_score(String communication_score) {
		this.communication_score = communication_score;
	}
	public String getInitiative_score() {
		return initiative_score;
	}
	public void setInitiative_score(String initiative_score) {
		this.initiative_score = initiative_score;
	}
	public String getLeadership_score() {
		return leadership_score;
	}
	public void setLeadership_score(String leadership_score) {
		this.leadership_score = leadership_score;
	}
	public String getExtra_curricular_score() {
		return extra_curricular_score;
	}
	public void setExtra_curricular_score(String extra_curricular_score) {
		this.extra_curricular_score = extra_curricular_score;
	}
	public String getTotal_score() {
		return total_score;
	}
	public void setTotal_score(String total_score) {
		this.total_score = total_score;
	}
	public String getProject_comments() {
		return project_comments;
	}
	public void setProject_comments(String project_comments) {
		this.project_comments = project_comments;
	}
	public String getAttendance_comments() {
		return attendance_comments;
	}
	public void setAttendance_comments(String attendance_comments) {
		this.attendance_comments = attendance_comments;
	}
	public String getTeam_collaboration_comments() {
		return team_collaboration_comments;
	}
	public void setTeam_collaboration_comments(String team_collaboration_comments) {
		this.team_collaboration_comments = team_collaboration_comments;
	}
	public String getCommunication_comments() {
		return communication_comments;
	}
	public void setCommunication_comments(String communication_comments) {
		this.communication_comments = communication_comments;
	}
	public String getInitiative_comments() {
		return initiative_comments;
	}
	public void setInitiative_comments(String initiative_comments) {
		this.initiative_comments = initiative_comments;
	}
	public String getLeadership_comments() {
		return leadership_comments;
	}
	public void setLeadership_comments(String leadership_comments) {
		this.leadership_comments = leadership_comments;
	}
	public String getExtra_curricular_comments() {
		return extra_curricular_comments;
	}
	public void setExtra_curricular_comments(String extra_curricular_comments) {
		this.extra_curricular_comments = extra_curricular_comments;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getQuarter_year() {
		return quarter_year;
	}
	public void setQuarter_year(String quarter_year) {
		this.quarter_year = quarter_year;
	}
	public String getApprisal_admin_flag() {
		return apprisal_admin_flag;
	}
	public void setApprisal_admin_flag(String apprisal_admin_flag) {
		this.apprisal_admin_flag = apprisal_admin_flag;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_project_score() {
		return user_project_score;
	}
	public void setUser_project_score(String user_project_score) {
		this.user_project_score = user_project_score;
	}
	public String getUser_attendance_score() {
		return user_attendance_score;
	}
	public void setUser_attendance_score(String user_attendance_score) {
		this.user_attendance_score = user_attendance_score;
	}
	public String getUser_team_collaboration_score() {
		return user_team_collaboration_score;
	}
	public void setUser_team_collaboration_score(String user_team_collaboration_score) {
		this.user_team_collaboration_score = user_team_collaboration_score;
	}
	public String getUser_communication_score() {
		return user_communication_score;
	}
	public void setUser_communication_score(String user_communication_score) {
		this.user_communication_score = user_communication_score;
	}
	public String getUser_initiative_score() {
		return user_initiative_score;
	}
	public void setUser_initiative_score(String user_initiative_score) {
		this.user_initiative_score = user_initiative_score;
	}
	public String getUser_leadership_score() {
		return user_leadership_score;
	}
	public void setUser_leadership_score(String user_leadership_score) {
		this.user_leadership_score = user_leadership_score;
	}
	public String getUser_extra_curricular_score() {
		return user_extra_curricular_score;
	}
	public void setUser_extra_curricular_score(String user_extra_curricular_score) {
		this.user_extra_curricular_score = user_extra_curricular_score;
	}
	public String getUser_total_score() {
		return user_total_score;
	}
	public void setUser_total_score(String user_total_score) {
		this.user_total_score = user_total_score;
	}
	
	public String getEmp_project_comment() {
		return emp_project_comment;
	}
	public void setEmp_project_comment(String emp_project_comment) {
		this.emp_project_comment = emp_project_comment;
	}
	public String getEmp_attendance_comment() {
		return emp_attendance_comment;
	}
	public void setEmp_attendance_comment(String emp_attendance_comment) {
		this.emp_attendance_comment = emp_attendance_comment;
	}
	public String getEmp_team_collaberation_comment() {
		return emp_team_collaberation_comment;
	}
	public void setEmp_team_collaberation_comment(String emp_team_collaberation_comment) {
		this.emp_team_collaberation_comment = emp_team_collaberation_comment;
	}
	public String getEmp_communication_comment() {
		return emp_communication_comment;
	}
	public void setEmp_communication_comment(String emp_communication_comment) {
		this.emp_communication_comment = emp_communication_comment;
	}
	public String getEmp_initiative_comment() {
		return emp_initiative_comment;
	}
	public void setEmp_initiative_comment(String emp_initiative_comment) {
		this.emp_initiative_comment = emp_initiative_comment;
	}
	public String getEmp_leadership_comment() {
		return emp_leadership_comment;
	}
	public void setEmp_leadership_comment(String emp_leadership_comment) {
		this.emp_leadership_comment = emp_leadership_comment;
	}
	public String getEmp_extra_curr_comment() {
		return emp_extra_curr_comment;
	}
	public void setEmp_extra_curr_comment(String emp_extra_curr_comment) {
		this.emp_extra_curr_comment = emp_extra_curr_comment;
	}
	
	@Override
	public String toString() {
		return "AppraisalFromEmployee_Entity [id=" + id + ", project_score=" + project_score + ", attendance_score="
				+ attendance_score + ", team_collaboration_score=" + team_collaboration_score + ", communication_score="
				+ communication_score + ", initiative_score=" + initiative_score + ", leadership_score="
				+ leadership_score + ", extra_curricular_score=" + extra_curricular_score + ", total_score="
				+ total_score + ", project_comments=" + project_comments + ", attendance_comments="
				+ attendance_comments + ", team_collaboration_comments=" + team_collaboration_comments
				+ ", communication_comments=" + communication_comments + ", initiative_comments=" + initiative_comments
				+ ", leadership_comments=" + leadership_comments + ", extra_curricular_comments="
				+ extra_curricular_comments + ", emp_id=" + emp_id + ", quarter=" + quarter + ", quarter_year="
				+ quarter_year + ", apprisal_admin_flag=" + apprisal_admin_flag + ", department=" + department
				+ ", user_id=" + user_id + ", user_project_score=" + user_project_score + ", user_attendance_score="
				+ user_attendance_score + ", user_team_collaboration_score=" + user_team_collaboration_score
				+ ", user_communication_score=" + user_communication_score + ", user_initiative_score="
				+ user_initiative_score + ", user_leadership_score=" + user_leadership_score
				+ ", user_extra_curricular_score=" + user_extra_curricular_score + ", user_total_score="
				+ user_total_score + ", emp_project_comment=" + emp_project_comment + ", emp_attendance_comment="
				+ emp_attendance_comment + ", emp_team_collaberation_comment=" + emp_team_collaberation_comment
				+ ", emp_communication_comment=" + emp_communication_comment + ", emp_initiative_comment="
				+ emp_initiative_comment + ", emp_leadership_comment=" + emp_leadership_comment
				+ ", emp_extra_curr_comment=" + emp_extra_curr_comment + ", demerit_point=" + demerit_point
				+ ", demerit_comment=" + demerit_comment + "]";
	}
}
