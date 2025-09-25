package com.example.mspl_connect.PayslipEntity;

public class EmployeeForPaySlip {
	
	private String name;
    private String employeeId;
    private String department;
    private String designation;
    private int days;
    private int workedDays; 
    private String month;
    private String esi_num;
    
    private Boolean EmailSent;

  //getter and setter method;
   public Boolean getEmailSent() {
  		return EmailSent;
  	}
  	public void setEmailSent(Boolean emailSent) {
  		EmailSent = emailSent;
  	}

    
	public String getEsi_num() {
		return esi_num;
	}
	public void setEsi_num(String esi_num) {
		this.esi_num = esi_num;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	private String email;
    private String dateOfJoining;
    private String pfNumber;
    private String bankAccount;
    private String bankName;
    private String pan;
    private double basic;
    private double hra;
    private double conveyance;
    
    private double arrears;
    private double bounce;
    
	private double medical;
    private double otherAllowance;
    private double otherAllowance1;
    private double reimbursement;
    private double totalEarnings;
    private double pfDeduction;
    private double incomeTax;
    private double esi;
    private double loan;
    private double otherDeductions;
    private double otherDeductions1;
    public double getOtherDeductions1() {
		return otherDeductions1;
	}
	public void setOtherDeductions1(double otherDeductions1) {
		this.otherDeductions1 = otherDeductions1;
	}
	private double totalDeductions;
    private double netSalary;
    public double getPt() {
		return pt;
	}
	public void setPt(double pt) {
		this.pt = pt;
	}
	private Long uanNo;
    private double esiDeduction;
    private double pt;
    
    
    

    
    public double getEsiDeduction() {
		return esiDeduction;
	}
	public void setEsiDeduction(double esiDeduction) {
		this.esiDeduction = esiDeduction;
	}
	public double getOtherAllowance1() {
		return otherAllowance1;
	}
	public void setOtherAllowance1(double otherAllowance1) {
		this.otherAllowance1 = otherAllowance1;
	}
	public int getWorkedDays() {
		return workedDays;
	}
	public void setWorkedDays(int workedDays) {
		this.workedDays = workedDays;
	}
    public double getArrears() {
		return arrears;
	}
	public void setArrears(double arrears) {
		this.arrears = arrears;
	}
	public double getBounce() {
		return bounce;
	}
	public void setBounce(double bounce) {
		this.bounce = bounce;
	}
	public EmployeeForPaySlip() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public Long getUanNo() {
		return uanNo;
	}
	public void setUanNo(Long uanNo) {
		this.uanNo = uanNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getPfNumber() {
		return pfNumber;
	}
	public void setPfNumber(String pfNumber) {
		this.pfNumber = pfNumber;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public double getBasic() {
		System.out.println("getBasicSal"+basic);
		return basic;
	}
	public void setBasic(double basic) {
		System.out.println("setBasicSal"+basic);
		this.basic = basic;
	}
	public double getHra() {
		return hra;
	}
	public void setHra(double hra) {
		this.hra = hra;
	}
	public double getConveyance() {
		return conveyance;
	}
	public void setConveyance(double conveyance) {
		this.conveyance = conveyance;
	}
	public double getMedical() {
		return medical;
	}
	public void setMedical(double medical) {
		this.medical = medical;
	}
	public double getOtherAllowance() {
		return otherAllowance;
	}
	public void setOtherAllowance(double otherAllowance) {
		this.otherAllowance = otherAllowance;
	}
	public double getReimbursement() {
		return reimbursement;
	}
	public void setReimbursement(double reimbursement) {
		this.reimbursement = reimbursement;
	}
	public double getTotalEarnings() {
		return totalEarnings;
	}
	public void setTotalEarnings(double totalEarnings) {
		this.totalEarnings = totalEarnings;
	}
	public double getPfDeduction() {
		return pfDeduction;
	}
	public void setPfDeduction(double pfDeduction) {
		this.pfDeduction = pfDeduction;
	}
	public double getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(double incomeTax) {
		this.incomeTax = incomeTax;
	}
	public double getEsi() {
		return esi;
	}
	public void setEsi(double esi) {
		this.esi = esi;
	}
	public double getLoan() {
		return loan;
	}
	public void setLoan(double loan) {
		this.loan = loan;
	}
	public double getOtherDeductions() {
		return otherDeductions;
	}
	public void setOtherDeductions(double otherDeductions) {
		this.otherDeductions = otherDeductions;
	}
	public double getTotalDeductions() {
		return totalDeductions;
	}
	public void setTotalDeductions(double totalDeductions) {
		this.totalDeductions = totalDeductions;
	}
	public double getNetSalary() {
		return netSalary;
	}
	public void setNetSalary(double netSalary) {
		this.netSalary = netSalary;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", employeeId=" + employeeId + ", department=" + department + ", designation="
				+ designation + ", days=" + days + ", workedDays=" + workedDays + ", month=" + month + ", esi_num="
				+ esi_num + ", email=" + email + ", dateOfJoining=" + dateOfJoining + ", pfNumber=" + pfNumber
				+ ", bankAccount=" + bankAccount + ", bankName=" + bankName + ", pan=" + pan + ", basic=" + basic
				+ ", hra=" + hra + ", conveyance=" + conveyance + ", arrears=" + arrears + ", bounce=" + bounce
				+ ", medical=" + medical + ", otherAllowance=" + otherAllowance + ", otherAllowance1=" + otherAllowance1
				+ ", reimbursement=" + reimbursement + ", totalEarnings=" + totalEarnings + ", pfDeduction="
				+ pfDeduction + ", incomeTax=" + incomeTax + ", esi=" + esi + ", loan=" + loan + ", otherDeductions="
				+ otherDeductions + ", otherDeductions1=" + otherDeductions1 + ", totalDeductions=" + totalDeductions
				+ ", netSalary=" + netSalary + ", uanNo=" + uanNo + ", esiDeduction=" + esiDeduction + ", pt=" + pt
				+ "]";
	}
	public EmployeeForPaySlip(String name, String employeeId, String department, String designation, int days, int workedDays,
			String month, String esi_num, String email, String dateOfJoining, String pfNumber, String bankAccount,
			String bankName, String pan, double basic, double hra, double conveyance, double arrears, double bounce,
			double medical, double otherAllowance, double otherAllowance1, double reimbursement, double totalEarnings,
			double pfDeduction, double incomeTax, double esi, double loan, double otherDeductions,
			double otherDeductions1, double totalDeductions, double netSalary, Long uanNo, double esiDeduction,
			double pt) {
		super();
		this.name = name;
		this.employeeId = employeeId;
		this.department = department;
		this.designation = designation;
		this.days = days;
		this.workedDays = workedDays;
		this.month = month;
		this.esi_num = esi_num;
		this.email = email;
		this.dateOfJoining = dateOfJoining;
		this.pfNumber = pfNumber;
		this.bankAccount = bankAccount;
		this.bankName = bankName;
		this.pan = pan;
		this.basic = basic;
		this.hra = hra;
		this.conveyance = conveyance;
		this.arrears = arrears;
		this.bounce = bounce;
		this.medical = medical;
		this.otherAllowance = otherAllowance;
		this.otherAllowance1 = otherAllowance1;
		this.reimbursement = reimbursement;
		this.totalEarnings = totalEarnings;
		this.pfDeduction = pfDeduction;
		this.incomeTax = incomeTax;
		this.esi = esi;
		this.loan = loan;
		this.otherDeductions = otherDeductions;
		this.otherDeductions1 = otherDeductions1;
		this.totalDeductions = totalDeductions;
		this.netSalary = netSalary;
		this.uanNo = uanNo;
		this.esiDeduction = esiDeduction;
		this.pt = pt;
	}
	public EmployeeForPaySlip(String email, String month) {
		this.email = email;
        this.month = month;
	}

}
