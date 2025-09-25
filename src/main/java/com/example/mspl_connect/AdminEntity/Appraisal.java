package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "appraisal_employee")
public class Appraisal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "project_score")
	private int projectScore;

	@Column(name = "attendance_score")
	private int attendanceScore;

	@Column(name = "team_collaboration_score")
	private int teamCollaborationScore;

	@Column(name = "communication_score")
	private int communicationScore;

	@Column(name = "initiative_score")
	private int initiativeScore;

	@Column(name = "leadership_score")
	private int leadershipScore;

	@Column(name = "extra_curricular_score")
	private int extraCurricularScore;

	@Column(name = "total_score")
	private int totalScore;

	    
	    @Column(name = "project_comments")
	    private String projectComments;

	    @Column(name = "attendance_comments")
	    private String attendanceComments;

	    @Column(name = "team_collaboration_comments")
	    private String teamCollaborationComments;

	    @Column(name = "communication_comments")
	    private String communicationComments;

	    @Column(name = "initiative_comments")
	    private String initiativeComments;

	    @Column(name = "leadership_comments")
	    private String leadershipComments;

	    @Column(name = "extra_curricular_comments")
	    private String extraCurricularComments;
	    
	    @Column(name="emp_id")
	    private String empId;
	    
	    @Column(name="department")
	    private String department;
	    
	    @Column(name="apprisal_admin_flag")
	    private String apprisalAdminFlag;
	    
	    @Column(name="quarter")
	    private String quarter;
	    
	    @Column(name="quarter_year")
	    private String quarter_year;
	    
	    
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
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public int getProjectScore() {
			return projectScore;
		}
		public void setProjectScore(int projectScore) {
			this.projectScore = projectScore;
		}
		public int getAttendanceScore() {
			return attendanceScore;
		}
		public void setAttendanceScore(int attendanceScore) {
			this.attendanceScore = attendanceScore;
		}
		public int getTeamCollaborationScore() {
			return teamCollaborationScore;
		}
		public void setTeamCollaborationScore(int teamCollaborationScore) {
			this.teamCollaborationScore = teamCollaborationScore;
		}
		public int getCommunicationScore() {
			return communicationScore;
		}
		public void setCommunicationScore(int communicationScore) {
			this.communicationScore = communicationScore;
		}
		public int getInitiativeScore() {
			return initiativeScore;
		}
		public void setInitiativeScore(int initiativeScore) {
			this.initiativeScore = initiativeScore;
		}
		public int getLeadershipScore() {
			return leadershipScore;
		}
		public void setLeadershipScore(int leadershipScore) {
			this.leadershipScore = leadershipScore;
		}
		public int getExtraCurricularScore() {
			return extraCurricularScore;
		}
		public void setExtraCurricularScore(int extraCurricularScore) {
			this.extraCurricularScore = extraCurricularScore;
		}
		public int getTotalScore() {
			return totalScore;
		}
		public void setTotalScore(int totalScore) {
			this.totalScore = totalScore;
		}
		
		@Override
		public String toString() {
			return "Appraisal [id=" + id + ", projectScore=" + projectScore + ", attendanceScore=" + attendanceScore
					+ ", teamCollaborationScore=" + teamCollaborationScore + ", communicationScore="
					+ communicationScore + ", initiativeScore=" + initiativeScore + ", leadershipScore="
					+ leadershipScore + ", extraCurricularScore=" + extraCurricularScore + ", totalScore=" + totalScore
					+ ", projectComments=" + projectComments + ", attendanceComments=" + attendanceComments
					+ ", teamCollaborationComments=" + teamCollaborationComments + ", communicationComments="
					+ communicationComments + ", initiativeComments=" + initiativeComments + ", leadershipComments="
					+ leadershipComments + ", extraCurricularComments=" + extraCurricularComments + ", empId=" + empId
					+ ", department=" + department + ", apprisalAdminFlag=" + apprisalAdminFlag + ", quarter=" + quarter
					+ ", quarter_year=" + quarter_year + "]";
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getApprisalAdminFlag() {
			return apprisalAdminFlag;
		}
		public void setApprisalAdminFlag(String apprisalAdminFlag) {
			this.apprisalAdminFlag = apprisalAdminFlag;
		}
		public String getProjectComments() {
			return projectComments;
		}
		public void setProjectComments(String projectComments) {
			this.projectComments = projectComments;
		}
		public String getAttendanceComments() {
			return attendanceComments;
		}
		public void setAttendanceComments(String attendanceComments) {
			this.attendanceComments = attendanceComments;
		}
		public String getTeamCollaborationComments() {
			return teamCollaborationComments;
		}
		public void setTeamCollaborationComments(String teamCollaborationComments) {
			this.teamCollaborationComments = teamCollaborationComments;
		}
		public String getCommunicationComments() {
			return communicationComments;
		}
		public void setCommunicationComments(String communicationComments) {
			this.communicationComments = communicationComments;
		}
		public String getInitiativeComments() {
			return initiativeComments;
		}
		public void setInitiativeComments(String initiativeComments) {
			this.initiativeComments = initiativeComments;
		}
		public String getLeadershipComments() {
			return leadershipComments;
		}
		public void setLeadershipComments(String leadershipComments) {
			this.leadershipComments = leadershipComments;
		}
		public String getExtraCurricularComments() {
			return extraCurricularComments;
		}
		public void setExtraCurricularComments(String extraCurricularComments) {
			this.extraCurricularComments = extraCurricularComments;
		}
		public String getEmpId() {
			return empId;
		}
		public void setEmpId(String empId) {
			this.empId = empId;
		}
}
