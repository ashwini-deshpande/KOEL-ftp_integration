package com.talentica.talentpool.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"positionName", "positionCode", "positionOwnerName", "requisitionerName", "location", "departmentName", "subDepartmentName", "subSubDepartmentName", "sub3DepartmentName", "sub4DepartmentName", "vacancies", "hireByDate", "positionLevel", "positionReferalFees", "note", "responsibilities", "budgetItemName", "gradeName", "buName", "costCenterName", "typeOfVacancy", "replacementEmpCode", "degreeName", "branchName", "minimumExperienceYr", "maximumExperienceYr", "primarySkills", "secondarySkills", "requirements", "positionStatus", "feedbackDecision", "customFields","requisitionApprovalTemplateId","nextUserId","degreeNames"})
@XmlRootElement(name="PositionDetailsPojo")
public class PositionDetailsPojo
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @XmlElement(required=true)
  protected String positionName;
  @XmlElement(required=true)
  protected String positionCode;
  @XmlElement(required=true)
  protected String positionOwnerName;
  @XmlElement(required=true)
  protected String requisitionerName;
  @XmlElement(required=true)
  protected String location;
  @XmlElement(required=true)
  protected String departmentName;
  @XmlElement(required=true)
  protected String subDepartmentName;
  @XmlElement(required=true)
  protected String subSubDepartmentName;
  @XmlElement(required=true)
  protected String sub3DepartmentName;
  @XmlElement(required=true)
  protected String sub4DepartmentName;
  @XmlElement(required=true)
  protected String vacancies;
  @XmlElement(required=true)
  protected String hireByDate;
  @XmlElement(required=true)
  protected String positionLevel;
  @XmlElement(required=true)
  protected String positionReferalFees;
  @XmlElement(required=true)
  protected String note;
  @XmlElement(required=true)
  protected String responsibilities;
  @XmlElement(required=true)
  protected String budgetItemName;
  @XmlElement(required=true)
  protected String gradeName;
  @XmlElement(required=true)
  protected String buName;
  @XmlElement(required=true)
  protected String costCenterName;
  @XmlElement(required=true)
  protected String typeOfVacancy;
  @XmlElement(required=true)
  protected String replacementEmpCode;
  @XmlElement(required=true)
  protected String degreeName;
  @XmlElement(required=true)
  protected String branchName;
  @XmlElement(required=true)
  protected String minimumExperienceYr;
  @XmlElement(required=true)
  protected String maximumExperienceYr;
  @XmlElement(required=true)
  protected String primarySkills;
  @XmlElement(required=true)
  protected String secondarySkills;
  @XmlElement(required=true)
  protected String requirements;
  @XmlElement(required=true)
  protected String positionStatus;
  @XmlElement(required=true)
  protected String feedbackDecision;
  @XmlElement(required=true)
  private List<CustomFields> customFields;
  @XmlElement(required=true)
  protected String requisitionApprovalTemplateId;
  @XmlElement(required=true)
  protected String nextUserId;
  @XmlElement(required=true)
  private List<String> degreeNames;
  
  
  public String getPositionName()
  {
    return this.positionName;
  }
  
  public void setPositionName(String value)
  {
    this.positionName = value;
  }
  
  public String getPositionCode()
  {
    return this.positionCode;
  }
  
  public void setPositionCode(String value)
  {
    this.positionCode = value;
  }
  
  public String getPositionOwnerName()
  {
    return this.positionOwnerName;
  }
  
  public void setPositionOwnerName(String value)
  {
    this.positionOwnerName = value;
  }
  
  public String getRequisitionerName()
  {
    return this.requisitionerName;
  }
  
  public void setRequisitionerName(String value)
  {
    this.requisitionerName = value;
  }
  
  public String getLocation()
  {
    return this.location;
  }
  
  public void setLocation(String value)
  {
    this.location = value;
  }
  
  public String getDepartmentName()
  {
    return this.departmentName;
  }
  
  public void setDepartmentName(String value)
  {
    this.departmentName = value;
  }
  
  public String getSubDepartmentName()
  {
    return this.subDepartmentName;
  }
  
  public void setSubDepartmentName(String value)
  {
    this.subDepartmentName = value;
  }
  
  public String getSubSubDepartmentName()
  {
    return this.subSubDepartmentName;
  }
  
  public void setSubSubDepartmentName(String value)
  {
    this.subSubDepartmentName = value;
  }
  
  public String getSub3DepartmentName()
  {
    return this.sub3DepartmentName;
  }
  
  public void setSub3DepartmentName(String value)
  {
    this.sub3DepartmentName = value;
  }
  
  public String getSub4DepartmentName()
  {
    return this.sub4DepartmentName;
  }
  
  public void setSub4DepartmentName(String value)
  {
    this.sub4DepartmentName = value;
  }
  
  public String getVacancies()
  {
    return this.vacancies;
  }
  
  public void setVacancies(String value)
  {
    this.vacancies = value;
  }
  
  public String getHireByDate()
  {
    return this.hireByDate;
  }
  
  public void setHireByDate(String value)
  {
    this.hireByDate = value;
  }
  
  public String getPositionLevel()
  {
    return this.positionLevel;
  }
  
  public void setPositionLevel(String value)
  {
    this.positionLevel = value;
  }
  
  public String getPositionReferalFees()
  {
    return this.positionReferalFees;
  }
  
  public void setPositionReferalFees(String value)
  {
    this.positionReferalFees = value;
  }
  
  public String getNote()
  {
    return this.note;
  }
  
  public void setNote(String value)
  {
    this.note = value;
  }
  
  public String getResponsibilities()
  {
    return this.responsibilities;
  }
  
  public void setResponsibilities(String value)
  {
    this.responsibilities = value;
  }
  
  public String getBudgetItemName()
  {
    return this.budgetItemName;
  }
  
  public void setBudgetItemName(String value)
  {
    this.budgetItemName = value;
  }
  
  public String getGradeName()
  {
    return this.gradeName;
  }
  
  public void setGradeName(String value)
  {
    this.gradeName = value;
  }
  
  public String getBuName()
  {
    return this.buName;
  }
  
  public void setBuName(String value)
  {
    this.buName = value;
  }
  
  public String getCostCenterName()
  {
    return this.costCenterName;
  }
  
  public void setCostCenterName(String value)
  {
    this.costCenterName = value;
  }
  
  public String getTypeOfVacancy()
  {
    return this.typeOfVacancy;
  }
  
  public void setTypeOfVacancy(String value)
  {
    this.typeOfVacancy = value;
  }
  
  public String getReplacementEmpCode()
  {
    return this.replacementEmpCode;
  }
  
  public void setReplacementEmpCode(String value)
  {
    this.replacementEmpCode = value;
  }
  
  public String getDegreeName()
  {
    return this.degreeName;
  }
  
  public void setDegreeName(String value)
  {
    this.degreeName = value;
  }
  
  public String getBranchName()
  {
    return this.branchName;
  }
  
  public void setBranchName(String value)
  {
    this.branchName = value;
  }
  
  public String getMinimumExperienceYr()
  {
    return this.minimumExperienceYr;
  }
  
  public void setMinimumExperienceYr(String value)
  {
    this.minimumExperienceYr = value;
  }
  
  public String getMaximumExperienceYr()
  {
    return this.maximumExperienceYr;
  }
  
  public void setMaximumExperienceYr(String value)
  {
    this.maximumExperienceYr = value;
  }
  
  public String getPrimarySkills()
  {
    return this.primarySkills;
  }
  
  public void setPrimarySkills(String value)
  {
    this.primarySkills = value;
  }
  
  public String getSecondarySkills()
  {
    return this.secondarySkills;
  }
  
  public void setSecondarySkills(String value)
  {
    this.secondarySkills = value;
  }
  
  public String getRequirements()
  {
    return this.requirements;
  }
  
  public void setRequirements(String value)
  {
    this.requirements = value;
  }
  
  public String getPositionStatus()
  {
    return this.positionStatus;
  }
  
  public void setPositionStatus(String value)
  {
    this.positionStatus = value;
  }
  
  public String getFeedbackDecision()
  {
    return this.feedbackDecision;
  }
  
  public void setFeedbackDecision(String value)
  {
    this.feedbackDecision = value;
  }
  
  public List<CustomFields> getCustomFields()
  {
    if (this.customFields == null) {
      this.customFields = new ArrayList<>();
    }
    return this.customFields;
  }

  public void setCustomFields(List<CustomFields> customFields) {
	this.customFields = customFields;
}

public String getRequisitionApprovalTemplateId() {
	return requisitionApprovalTemplateId;
  }

 public void setRequisitionApprovalTemplateId(String requisitionApprovalTemplateId) {
	this.requisitionApprovalTemplateId = requisitionApprovalTemplateId;
 }

 public String getNextUserId() {
	return nextUserId;
 }

 public void setNextUserId(String nextUserId) {
	this.nextUserId = nextUserId;
 }

public List<String> getDegreeNames() {
	return degreeNames;
}

public void setDegreeNames(List<String> degreeNames) {
	this.degreeNames = degreeNames;
}

}
