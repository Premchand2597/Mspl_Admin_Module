package com.example.mspl_connect.AdminEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "apprisal_by_admin")
public class ApprisalForAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "emp_id")
    private String empId;
        
    @Column(name = "dept")
    private String dept;
    
    @Column(name = "quarter")
    private String quarter;
    
    @Column(name = "quarter_year")
    private String quarterYear;

    @Column(name = "project_planning_ratings", nullable = false)
    private int projectPlanningRatings;

    @Column(name = "project_planning_desc")
    private String projectPlanningDesc;

    @Column(name = "team_leadership_ratings", nullable = false)
    private int teamLeadershipRatings;

    @Column(name = "team_leadership_desc")
    private String teamLeadershipDesc;

    @Column(name = "stakeholder_comm_ratings", nullable = false)
    private int stakeholderCommRatings;

    @Column(name = "stakeholder_comm_desc")
    private String stakeholderCommDesc;

    @Column(name = "budget_cost_ratings", nullable = false)
    private int budgetCostRatings;

    @Column(name = "budget_cost_description")
    private String budgetCostDescription;

    @Column(name = "risk_management_ratings", nullable = false)
    private int riskManagementRatings;

    @Column(name = "risk_management_desc")
    private String riskManagementDesc;

    @Column(name = "quality_assurance_ratings", nullable = false)
    private int qualityAssuranceRatings;

    @Column(name = "quality_assurance_desc")
    private String qualityAssuranceDesc;
    
    @Column(name = "total_score")
    private Integer total_score;

    @Column(name ="project_planning_points")
	private String projectPlanningPoints;
    
    @Column(name ="team_leadership_points")
	private String teamLeadershipPoints;
    
    @Column(name ="stakeholder_comm_points")
	private String stakeholderCommPoints;
    
    @Column(name ="budget_cost_points")
   	private String budgetCostPoints;
    
    @Column(name ="risk_management_points")																																						
   	private String riskManagementPoints;
    
    @Column(name ="quality_assurance_points")
   	private String qualityAssurancePoints;

    @Column(name = "submitted_date")
    private String submitted_date;
    
    
	public String getSubmitted_date() {
		return submitted_date;
	}

	public void setSubmitted_date(String submitted_date) {
		this.submitted_date = submitted_date;
	}

	public String getProjectPlanningPoints() {
		return projectPlanningPoints;
	}

	public void setProjectPlanningPoints(String projectPlanningPoints) {
		this.projectPlanningPoints = projectPlanningPoints;
	}

	public String getTeamLeadershipPoints() {
		return teamLeadershipPoints;
	}

	public void setTeamLeadershipPoints(String teamLeadershipPoints) {
		this.teamLeadershipPoints = teamLeadershipPoints;
	}

	public String getStakeholderCommPoints() {
		return stakeholderCommPoints;
	}

	public void setStakeholderCommPoints(String stakeholderCommPoints) {
		this.stakeholderCommPoints = stakeholderCommPoints;
	}

	public String getBudgetCostPoints() {
		return budgetCostPoints;
	}

	public void setBudgetCostPoints(String budgetCostPoints) {
		this.budgetCostPoints = budgetCostPoints;
	}

	public String getRiskManagementPoints() {
		return riskManagementPoints;
	}

	public void setRiskManagementPoints(String riskManagementPoints) {
		this.riskManagementPoints = riskManagementPoints;
	}

	public String getQualityAssurancePoints() {
		return qualityAssurancePoints;
	}

	public void setQualityAssurancePoints(String qualityAssurancePoints) {
		this.qualityAssurancePoints = qualityAssurancePoints;
	}

	public Integer getTotal_score() {
		return total_score;
	}

	public void setTotal_score(Integer total_score) {
		this.total_score = total_score;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getQuarterYear() {
		return quarterYear;
	}

	public void setQuarterYear(String quarterYear) {
		this.quarterYear = quarterYear;
	}

	public int getProjectPlanningRatings() {
		return projectPlanningRatings;
	}

	public void setProjectPlanningRatings(int projectPlanningRatings) {
		this.projectPlanningRatings = projectPlanningRatings;
	}

	public String getProjectPlanningDesc() {
		return projectPlanningDesc;
	}

	public void setProjectPlanningDesc(String projectPlanningDesc) {
		this.projectPlanningDesc = projectPlanningDesc;
	}

	public int getTeamLeadershipRatings() {
		return teamLeadershipRatings;
	}

	public void setTeamLeadershipRatings(int teamLeadershipRatings) {
		this.teamLeadershipRatings = teamLeadershipRatings;
	}

	public String getTeamLeadershipDesc() {
		return teamLeadershipDesc;
	}

	public void setTeamLeadershipDesc(String teamLeadershipDesc) {
		this.teamLeadershipDesc = teamLeadershipDesc;
	}

	public int getStakeholderCommRatings() {
		return stakeholderCommRatings;
	}

	public void setStakeholderCommRatings(int stakeholderCommRatings) {
		this.stakeholderCommRatings = stakeholderCommRatings;
	}

	public String getStakeholderCommDesc() {
		return stakeholderCommDesc;
	}

	public void setStakeholderCommDesc(String stakeholderCommDesc) {
		this.stakeholderCommDesc = stakeholderCommDesc;
	}

	public int getBudgetCostRatings() {
		return budgetCostRatings;
	}

	public void setBudgetCostRatings(int budgetCostRatings) {
		this.budgetCostRatings = budgetCostRatings;
	}

	public String getBudgetCostDescription() {
		return budgetCostDescription;
	}

	public void setBudgetCostDescription(String budgetCostDescription) {
		this.budgetCostDescription = budgetCostDescription;
	}

	public int getRiskManagementRatings() {
		return riskManagementRatings;
	}

	public void setRiskManagementRatings(int riskManagementRatings) {
		this.riskManagementRatings = riskManagementRatings;
	}

	public String getRiskManagementDesc() {
		return riskManagementDesc;
	}

	public void setRiskManagementDesc(String riskManagementDesc) {
		this.riskManagementDesc = riskManagementDesc;
	}

	public int getQualityAssuranceRatings() {
		return qualityAssuranceRatings;
	}

	public void setQualityAssuranceRatings(int qualityAssuranceRatings) {
		this.qualityAssuranceRatings = qualityAssuranceRatings;
	}

	public String getQualityAssuranceDesc() {
		return qualityAssuranceDesc;
	}

	public void setQualityAssuranceDesc(String qualityAssuranceDesc) {
		this.qualityAssuranceDesc = qualityAssuranceDesc;
	}

	@Override
	public String toString() {
		return "ApprisalForAdmin [id=" + id + ", empId=" + empId + ", dept=" + dept + ", quarter=" + quarter
				+ ", quarterYear=" + quarterYear + ", projectPlanningRatings=" + projectPlanningRatings
				+ ", projectPlanningDesc=" + projectPlanningDesc + ", teamLeadershipRatings=" + teamLeadershipRatings
				+ ", teamLeadershipDesc=" + teamLeadershipDesc + ", stakeholderCommRatings=" + stakeholderCommRatings
				+ ", stakeholderCommDesc=" + stakeholderCommDesc + ", budgetCostRatings=" + budgetCostRatings
				+ ", budgetCostDescription=" + budgetCostDescription + ", riskManagementRatings="
				+ riskManagementRatings + ", riskManagementDesc=" + riskManagementDesc + ", qualityAssuranceRatings="
				+ qualityAssuranceRatings + ", qualityAssuranceDesc=" + qualityAssuranceDesc + ", total_score="
				+ total_score + ", projectPlanningPoints=" + projectPlanningPoints + ", teamLeadershipPoints="
				+ teamLeadershipPoints + ", stakeholderCommPoints=" + stakeholderCommPoints + ", budgetCostPoints="
				+ budgetCostPoints + ", riskManagementPoints=" + riskManagementPoints + ", qualityAssurancePoints="
				+ qualityAssurancePoints + ", submitted_date=" + submitted_date + "]";
	}

	
}
