package com.talentica.talentpool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({ "classpath:requisitionConfig.properties" })
public class RequisitionConfigProperties {

	@Value("${position.xls.file.path}")
	private String positionFilePath;
	
	@Value("${processed.xls.file.path}")
	private String processedExcelsPath;

	@Value("${position.field.default_min_exp}")
	private String minExp;

	@Value("${position.field.default_max_exp}")
	private String maxExp;

	@Value("${position.requisitioner.approval.templateId}")
	private String requisitionApprovalTemplateId;

	@Value("${position.field.hire_by_date}")
	private String hireByDate;

	@Value("${position.custom.field.irc_code}")
	private String ircCode;

	@Value("${position.custom.field.start_date}")
	private String startDate;

	@Value("${position.custom.field.end_date}")
	private String endDate;

	@Value("${position.custom.field.irc_creation_or_approved_date}")
	private String approvedDate;

	@Value("${position.field.department}")
	private String department;

	@Value("${position.field.sub_department}")
	private String subDepartement;

	@Value("${position.field.location}")
	private String location;

	@Value("${position.field.grade}")
	private String grade;

	@Value("${position.field.vacancy}")
	private String vacancy;

	@Value("${position.field.requisitioner}")
	private String requisitioner;

	@Value("${position.field.owner}")
	private String owner;

	@Value("${position.field.status}")
	private String status;

	@Value("${position.field.vacancy_type}")
	private String vacancyType;

	@Value("${position.field.replacement_for}")
	private String replacementFor;

	@Value("${position.custom.field.separation_date}")
	private String separationDate;

	@Value("${position.custom.field.vacancy_within_business_plan}")
	private String vacancyWithinBP;

	@Value("${position.custom.field.reason_if_above_is_no}")
	private String reason;

	@Value("${position.custom.field.is_this_job_being_done_now}")
	private String jobBeingDoneNow;

	@Value("${position.custom.field.how_and_by_whom_if_above_is_Yes}")
	private String howAndByWhom;

	@Value("${position.custom.field.seating_arrangement}")
	private String seatingArrangement;

	@Value("${position.custom.field.internal_telephone}")
	private String internalTelephone;

	@Value("${position.custom.field.zero_dial_facility}")
	private String zeroDialFacility;

	@Value("${position.custom.field.desktop_computer}")
	private String desktopComputer;

	@Value("${position.custom.field.laptop}")
	private String laptop;

	@Value("${position.custom.field.sim_card}")
	private String simCard;

	@Value("${position.custom.field.data_card}")
	private String dataCard;

	@Value("${position.custom.field.professional_area}")
	private String professionalArea;

	@Value("${position.custom.field.suitable_for_employee}")
	private String suitableForEmployee;

	@Value("${position.custom.field.suitable_for_contractor}")
	private String suitableForContractor;

	@Value("${position.custom.field.employment_status}")
	private String employmentStatus;

	@Value("${position.custom.field.travel_information_amount_of_travel}")
	private String travelInformation;
	
	@Value("${position.custom.field.yrs_of_experience}")
	private String yrsOfExperience;

	@Value("${position.field.position_name}")
	private String positionName;

	@Value("${position.custom.field.organization_description}")
	private String organizationDescription;

	@Value("${position.custom.field.brief_job_description}")
	private String briefJobDescription;

	@Value("${position.field.detailed_job_description}")
	private String detailedJobDescription;

	@Value("${position.custom.field.kra}")
	private String kra;

	@Value("${position.custom.field.kra_question_how_does_this_position_contribute_to_the_profitability_of_the_company_please_give_quantitative}")
	private String kra1;

	@Value("${position.custom.field.kra_question_how_would_addition_of_this_manpower_improve_service_levels_to_your_internal_external_customers_quantify_to_the_extent_possible}")
	private String kra2;

	@Value("${position.custom.field.min_salary}")
	private String minSalary;

	@Value("${position.custom.field.max_salary}")
	private String maxSalary;

	@Value("${position.custom.field.salary_currency}")
	private String salaryCurrency;

	@Value("${position.custom.field.work_at_home}")
	private String workAtHome;

	@Value("${position.field.min_qual_level}")
	private String minQualLevel;

	@Value("${position.field.max_qual_level}")
	private String maxQualLevel;

	@Value("${position.field.skill}")
	private String skill;

	@Value("${position.custom.field.min_rating_level_name}")
	private String minRatingLevelName;

	@Value("${position.custom.field.max_rating_level_name}")
	private String maxRatingLevelName;

	@Value("${position.field.isfilled}")
	private String isFilled;

	public String getPositionFilePath() {
		return positionFilePath;
	}

	public void setPositionFilePath(String positionFilePath) {
		this.positionFilePath = positionFilePath;
	}
	
	public String getProcessedExcelsPath() {
		return processedExcelsPath;
	}

	public void setProcessedExcelsPath(String processedExcelsPath) {
		this.processedExcelsPath = processedExcelsPath;
	}

	public String getMinExp() {
		return minExp;
	}

	public void setMinExp(String minExp) {
		this.minExp = minExp;
	}

	public String getMaxExp() {
		return maxExp;
	}

	public void setMaxExp(String maxExp) {
		this.maxExp = maxExp;
	}

	public String getRequisitionApprovalTemplateId() {
		return requisitionApprovalTemplateId;
	}

	public void setRequisitionApprovalTemplateId(String requisitionApprovalTemplateId) {
		this.requisitionApprovalTemplateId = requisitionApprovalTemplateId;
	}

	public String getHireByDate() {
		return hireByDate;
	}

	public void setHireByDate(String hireByDate) {
		this.hireByDate = hireByDate;
	}

	public String getIrcCode() {
		return ircCode;
	}

	public void setIrcCode(String ircCode) {
		this.ircCode = ircCode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSubDepartement() {
		return subDepartement;
	}

	public void setSubDepartement(String subDepartement) {
		this.subDepartement = subDepartement;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getVacancy() {
		return vacancy;
	}

	public void setVacancy(String vacancy) {
		this.vacancy = vacancy;
	}

	public String getRequisitioner() {
		return requisitioner;
	}

	public void setRequisitioner(String requisitioner) {
		this.requisitioner = requisitioner;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVacancyType() {
		return vacancyType;
	}

	public void setVacancyType(String vacancyType) {
		this.vacancyType = vacancyType;
	}

	public String getReplacementFor() {
		return replacementFor;
	}

	public void setReplacementFor(String replacementFor) {
		this.replacementFor = replacementFor;
	}

	public String getSeparationDate() {
		return separationDate;
	}

	public void setSeparationDate(String separationDate) {
		this.separationDate = separationDate;
	}

	public String getVacancyWithinBP() {
		return vacancyWithinBP;
	}

	public void setVacancyWithinBP(String vacancyWithinBP) {
		this.vacancyWithinBP = vacancyWithinBP;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getJobBeingDoneNow() {
		return jobBeingDoneNow;
	}

	public void setJobBeingDoneNow(String jobBeingDoneNow) {
		this.jobBeingDoneNow = jobBeingDoneNow;
	}

	public String getHowAndByWhom() {
		return howAndByWhom;
	}

	public void setHowAndByWhom(String howAndByWhom) {
		this.howAndByWhom = howAndByWhom;
	}

	public String getSeatingArrangement() {
		return seatingArrangement;
	}

	public void setSeatingArrangement(String seatingArrangement) {
		this.seatingArrangement = seatingArrangement;
	}

	public String getInternalTelephone() {
		return internalTelephone;
	}

	public void setInternalTelephone(String internalTelephone) {
		this.internalTelephone = internalTelephone;
	}

	public String getZeroDialFacility() {
		return zeroDialFacility;
	}

	public void setZeroDialFacility(String zeroDialFacility) {
		this.zeroDialFacility = zeroDialFacility;
	}

	public String getDesktopComputer() {
		return desktopComputer;
	}

	public void setDesktopComputer(String desktopComputer) {
		this.desktopComputer = desktopComputer;
	}

	public String getLaptop() {
		return laptop;
	}

	public void setLaptop(String laptop) {
		this.laptop = laptop;
	}

	public String getSimCard() {
		return simCard;
	}

	public void setSimCard(String simCard) {
		this.simCard = simCard;
	}

	public String getDataCard() {
		return dataCard;
	}

	public void setDataCard(String dataCard) {
		this.dataCard = dataCard;
	}

	public String getProfessionalArea() {
		return professionalArea;
	}

	public void setProfessionalArea(String professionalArea) {
		this.professionalArea = professionalArea;
	}

	public String getSuitableForEmployee() {
		return suitableForEmployee;
	}

	public void setSuitableForEmployee(String suitableForEmployee) {
		this.suitableForEmployee = suitableForEmployee;
	}

	public String getSuitableForContractor() {
		return suitableForContractor;
	}

	public void setSuitableForContractor(String suitableForContractor) {
		this.suitableForContractor = suitableForContractor;
	}

	public String getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public String getTravelInformation() {
		return travelInformation;
	}

	public void setTravelInformation(String travelInformation) {
		this.travelInformation = travelInformation;
	}

	public String getYrsOfExperience() {
		return yrsOfExperience;
	}

	public void setYrsOfExperience(String yrsOfExperience) {
		this.yrsOfExperience = yrsOfExperience;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getOrganizationDescription() {
		return organizationDescription;
	}

	public void setOrganizationDescription(String organizationDescription) {
		this.organizationDescription = organizationDescription;
	}

	public String getBriefJobDescription() {
		return briefJobDescription;
	}

	public void setBriefJobDescription(String briefJobDescription) {
		this.briefJobDescription = briefJobDescription;
	}

	public String getDetailedJobDescription() {
		return detailedJobDescription;
	}

	public void setDetailedJobDescription(String detailedJobDescription) {
		this.detailedJobDescription = detailedJobDescription;
	}

	public String getKra() {
		return kra;
	}

	public void setKra(String kra) {
		this.kra = kra;
	}

	public String getKra1() {
		return kra1;
	}

	public void setKra1(String kra1) {
		this.kra1 = kra1;
	}

	public String getKra2() {
		return kra2;
	}

	public void setKra2(String kra2) {
		this.kra2 = kra2;
	}

	public String getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(String minSalary) {
		this.minSalary = minSalary;
	}

	public String getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(String maxSalary) {
		this.maxSalary = maxSalary;
	}

	public String getSalaryCurrency() {
		return salaryCurrency;
	}

	public void setSalaryCurrency(String salaryCurrency) {
		this.salaryCurrency = salaryCurrency;
	}

	public String getWorkAtHome() {
		return workAtHome;
	}

	public void setWorkAtHome(String workAtHome) {
		this.workAtHome = workAtHome;
	}

	public String getMinQualLevel() {
		return minQualLevel;
	}

	public void setMinQualLevel(String minQualLevel) {
		this.minQualLevel = minQualLevel;
	}

	public String getMaxQualLevel() {
		return maxQualLevel;
	}

	public void setMaxQualLevel(String maxQualLevel) {
		this.maxQualLevel = maxQualLevel;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getMinRatingLevelName() {
		return minRatingLevelName;
	}

	public void setMinRatingLevelName(String minRatingLevelName) {
		this.minRatingLevelName = minRatingLevelName;
	}

	public String getMaxRatingLevelName() {
		return maxRatingLevelName;
	}

	public void setMaxRatingLevelName(String maxRatingLevelName) {
		this.maxRatingLevelName = maxRatingLevelName;
	}

	public String getIsFilled() {
		return isFilled;
	}

	public void setIsFilled(String isFilled) {
		this.isFilled = isFilled;
	}

}
