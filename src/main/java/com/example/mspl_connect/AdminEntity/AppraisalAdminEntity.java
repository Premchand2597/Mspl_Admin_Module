package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "appraisal_admin")
public class AppraisalAdminEntity {

	public AppraisalAdminEntity() {
		super();
	}
	
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
	    
	    @Column(name="apprisal_admin_flag")
	    private int apprisal_admin_flag;
	    
	    @Column(name="quarter")
	    private String quarter;
	    
	    @Column(name="quarter_year")
	    private String quarter_year;
	    
	    @Column(name="department")
	    private String department;
	    
	    @Column(name = "demerit_point")
		private int demerit_point;
		
		@Column(name="demerit_comment")
		private String demerit_comment;
		
		@Column(name="validated_date")
		private String validated_date;
		
		@Column(name="validated_by")
		private String validated_by;
		
		public String getValidated_date() {
			return validated_date;
		}
		public void setValidated_date(String validated_date) {
			this.validated_date = validated_date;
		}
		public String getValidated_by() {
			return validated_by;
		}
		public void setValidated_by(String validated_by) {
			this.validated_by = validated_by;
		}
		public int getDemerit_point() {
			return demerit_point;
		}
		public void setDemerit_point(int demerit_point) {
			this.demerit_point = demerit_point;
		}
		public String getDemerit_comment() {
			return demerit_comment;
		}
		public void setDemerit_comment(String demerit_comment) {
			this.demerit_comment = demerit_comment;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
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
		public int getApprisal_admin_flag() {
			return apprisal_admin_flag;
		}
		public void setApprisal_admin_flag(int apprisal_admin_flag) {
			this.apprisal_admin_flag = apprisal_admin_flag;
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
		@Override
		public String toString() {
			return "AppraisalAdminEntity [id=" + id + ", projectScore=" + projectScore + ", attendanceScore="
					+ attendanceScore + ", teamCollaborationScore=" + teamCollaborationScore + ", communicationScore="
					+ communicationScore + ", initiativeScore=" + initiativeScore + ", leadershipScore="
					+ leadershipScore + ", extraCurricularScore=" + extraCurricularScore + ", totalScore=" + totalScore
					+ ", projectComments=" + projectComments + ", attendanceComments=" + attendanceComments
					+ ", teamCollaborationComments=" + teamCollaborationComments + ", communicationComments="
					+ communicationComments + ", initiativeComments=" + initiativeComments + ", leadershipComments="
					+ leadershipComments + ", extraCurricularComments=" + extraCurricularComments + ", empId=" + empId
					+ ", apprisal_admin_flag=" + apprisal_admin_flag + ", quarter=" + quarter + ", quarter_year="
					+ quarter_year + ", department=" + department + ", demerit_point=" + demerit_point
					+ ", demerit_comment=" + demerit_comment + ", validated_date=" + validated_date + ", validated_by="
					+ validated_by + "]";
		}

		
		public AppraisalAdminEntity(Long id, int projectScore, int attendanceScore, int teamCollaborationScore,
				int communicationScore, int initiativeScore, int leadershipScore, int extraCurricularScore,
				int totalScore, String projectComments, String attendanceComments, String teamCollaborationComments,
				String communicationComments, String initiativeComments, String leadershipComments,
				String extraCurricularComments, String empId, int apprisal_admin_flag, String quarter,
				String quarter_year, String department, int demerit_point, String demerit_comment,
				String validated_date, String validated_by) {
			super();
			this.id = id;
			this.projectScore = projectScore;
			this.attendanceScore = attendanceScore;
			this.teamCollaborationScore = teamCollaborationScore;
			this.communicationScore = communicationScore;
			this.initiativeScore = initiativeScore;
			this.leadershipScore = leadershipScore;
			this.extraCurricularScore = extraCurricularScore;
			this.totalScore = totalScore;
			this.projectComments = projectComments;
			this.attendanceComments = attendanceComments;
			this.teamCollaborationComments = teamCollaborationComments;
			this.communicationComments = communicationComments;
			this.initiativeComments = initiativeComments;
			this.leadershipComments = leadershipComments;
			this.extraCurricularComments = extraCurricularComments;
			this.empId = empId;
			this.apprisal_admin_flag = apprisal_admin_flag;
			this.quarter = quarter;
			this.quarter_year = quarter_year;
			this.department = department;
			this.demerit_point = demerit_point;
			this.demerit_comment = demerit_comment;
			this.validated_date = validated_date;
			this.validated_by = validated_by;
		}
		
}
