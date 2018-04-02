package com.talentica.talentpool.util;


import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.talentica.talentpool.RequisitionConfigProperties;
import com.talentica.talentpool.constants.ExcelColumnMappingConstants;

@Component
public class ExcelColumnMappingUtils {
	
	@Autowired
	private RequisitionConfigProperties requisitionConfigProperties;

	public Map<String,String> generatPositionFieldsMap(){
		Map<String,String> positionFields = new HashMap<>();
		positionFields.put(ExcelColumnMappingConstants.positionName, requisitionConfigProperties.getPositionName());
		positionFields.put(ExcelColumnMappingConstants.owner, requisitionConfigProperties.getOwner());
		positionFields.put(ExcelColumnMappingConstants.requisitioner, requisitionConfigProperties.getRequisitioner());
		positionFields.put(ExcelColumnMappingConstants.grade, requisitionConfigProperties.getGrade());
		positionFields.put(ExcelColumnMappingConstants.location, requisitionConfigProperties.getLocation());
		positionFields.put(ExcelColumnMappingConstants.department, requisitionConfigProperties.getDepartment());
		positionFields.put(ExcelColumnMappingConstants.subDepartment, requisitionConfigProperties.getSubDepartement());
		positionFields.put(ExcelColumnMappingConstants.vacancy, requisitionConfigProperties.getVacancy());
		positionFields.put(ExcelColumnMappingConstants.vacancyType, requisitionConfigProperties.getVacancyType());
		positionFields.put(ExcelColumnMappingConstants.replacementFor, requisitionConfigProperties.getReplacementFor());
		positionFields.put(ExcelColumnMappingConstants.detailedJobDescription, requisitionConfigProperties.getDetailedJobDescription());
		positionFields.put(ExcelColumnMappingConstants.skill, requisitionConfigProperties.getSkill());
		positionFields.put(ExcelColumnMappingConstants.minQualLevel, requisitionConfigProperties.getMinQualLevel());
		positionFields.put(ExcelColumnMappingConstants.maxQualLevel, requisitionConfigProperties.getMaxQualLevel());
		return positionFields;
	}
	
	public Map<String,String> generatPositionCustomFieldsMap(){
		Map<String,String> positionCustomFields = new HashMap<>();
		positionCustomFields.put(ExcelColumnMappingConstants.ircCode, requisitionConfigProperties.getIrcCode());
		positionCustomFields.put(ExcelColumnMappingConstants.separationDate, requisitionConfigProperties.getSeparationDate());
		positionCustomFields.put(ExcelColumnMappingConstants.vacancyWithinBusinessPlan, requisitionConfigProperties.getVacancyWithinBP());
		positionCustomFields.put(ExcelColumnMappingConstants.reasonIfAboveIsNo, requisitionConfigProperties.getReason());
		positionCustomFields.put(ExcelColumnMappingConstants.jobBeingDoneNow, requisitionConfigProperties.getJobBeingDoneNow());
		positionCustomFields.put(ExcelColumnMappingConstants.howAndByWhom, requisitionConfigProperties.getHowAndByWhom());
		positionCustomFields.put(ExcelColumnMappingConstants.seatingArrangement, requisitionConfigProperties.getSeatingArrangement());
		positionCustomFields.put(ExcelColumnMappingConstants.internalTelephone, requisitionConfigProperties.getInternalTelephone());
		positionCustomFields.put(ExcelColumnMappingConstants.zeroDialFacility, requisitionConfigProperties.getZeroDialFacility());
		positionCustomFields.put(ExcelColumnMappingConstants.desktopComputer, requisitionConfigProperties.getDesktopComputer());
		positionCustomFields.put(ExcelColumnMappingConstants.laptop, requisitionConfigProperties.getLaptop());
		positionCustomFields.put(ExcelColumnMappingConstants.simCard, requisitionConfigProperties.getSimCard());
		positionCustomFields.put(ExcelColumnMappingConstants.dataCard, requisitionConfigProperties.getDataCard());
		positionCustomFields.put(ExcelColumnMappingConstants.employmentStatus, requisitionConfigProperties.getEmploymentStatus());
		positionCustomFields.put(ExcelColumnMappingConstants.travelInformation, requisitionConfigProperties.getTravelInformation());
		positionCustomFields.put(ExcelColumnMappingConstants.workAtHome, requisitionConfigProperties.getWorkAtHome());
		positionCustomFields.put(ExcelColumnMappingConstants.approvedDate, requisitionConfigProperties.getApprovedDate());
		positionCustomFields.put(ExcelColumnMappingConstants.kra, requisitionConfigProperties.getKra());
		positionCustomFields.put(ExcelColumnMappingConstants.kra1, requisitionConfigProperties.getKra1());
		positionCustomFields.put(ExcelColumnMappingConstants.kra2, requisitionConfigProperties.getKra2());
		positionCustomFields.put(ExcelColumnMappingConstants.minRatingLevelName, requisitionConfigProperties.getMinRatingLevelName());
		positionCustomFields.put(ExcelColumnMappingConstants.maxRatingLevelName, requisitionConfigProperties.getMaxRatingLevelName());
		positionCustomFields.put(ExcelColumnMappingConstants.startDate, requisitionConfigProperties.getStartDate());
		positionCustomFields.put(ExcelColumnMappingConstants.endDate, requisitionConfigProperties.getEndDate());
		positionCustomFields.put(ExcelColumnMappingConstants.professionalArea, requisitionConfigProperties.getProfessionalArea());
		positionCustomFields.put(ExcelColumnMappingConstants.organizationDescription, requisitionConfigProperties.getOrganizationDescription());
		positionCustomFields.put(ExcelColumnMappingConstants.briefJobDescription, requisitionConfigProperties.getBriefJobDescription());
		positionCustomFields.put(ExcelColumnMappingConstants.yrsOfExperience, requisitionConfigProperties.getYrsOfExperience());
		positionCustomFields.put(ExcelColumnMappingConstants.suitableForEmployee, requisitionConfigProperties.getSuitableForEmployee());
		positionCustomFields.put(ExcelColumnMappingConstants.suitableForContractor, requisitionConfigProperties.getSuitableForContractor());
		positionCustomFields.put(ExcelColumnMappingConstants.minSalary, requisitionConfigProperties.getMinSalary());
		positionCustomFields.put(ExcelColumnMappingConstants.maxSalary, requisitionConfigProperties.getMaxSalary());
		positionCustomFields.put(ExcelColumnMappingConstants.salaryCurrency, requisitionConfigProperties.getSalaryCurrency());
		return positionCustomFields;
	}
}
