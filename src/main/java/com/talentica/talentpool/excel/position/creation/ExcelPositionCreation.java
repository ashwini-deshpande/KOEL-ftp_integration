package com.talentica.talentpool.excel.position.creation;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.talentica.talentpool.RequisitionConfigProperties;
import com.talentica.talentpool.constants.ExcelColumnMappingConstants;
import com.talentica.talentpool.models.CustomFields;
import com.talentica.talentpool.models.PositionDetailsPojo;
import com.talentica.talentpool.util.ExcelColumnMappingUtils;
import com.talentica.talentpool.util.FileUtil;

@Component
public class ExcelPositionCreation {

	private static final String DD_MM_YYYY = "dd/MM/yyyy";

	private static final String SPACE_DASH_SPACE = " - ";

	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelPositionCreation.class);

	@Autowired
	private RequisitionConfigProperties requisitionConfigProperties;

	@Autowired
	private ExcelColumnMappingUtils excelColumnMappingUtils;
	
	@Autowired
	private FileUtil fileUtil;
	
	public List<PositionDetailsPojo> getPositionDetaislPojoListFromExcel() throws IOException {
		File lastModifiedFile = fileUtil.getLatestFilefromDir();
		if (lastModifiedFile != null) {
			LOGGER.debug("Latest Excel file with its full path to create positions :  "
					+ lastModifiedFile.getAbsolutePath());
			Workbook wb = null;
			try {
				File f = new File(lastModifiedFile.getAbsolutePath());
				wb = WorkbookFactory.create(f);
				Sheet sheet = wb.getSheetAt(0);
				Row row = null;
				int fileStartRowIndex = 0;
				for (int i = 0; i < sheet.getLastRowNum(); i++) {
					row = sheet.getRow(i);
					if (row != null) {
						fileStartRowIndex = i;
						break;
					}
				}
				if (row != null) {
					Map<String, Integer> headersMap = fetchExcelHeader(row);
					return createPositionDetailsPojo(fileStartRowIndex, sheet, headersMap);
				} else {
					LOGGER.error("No Data to read.");
				}
			} catch (Exception e) {
				LOGGER.error("Exception occured while creating positions from xls file. ", e);
			} finally {
				if (wb != null)
					wb.close();
			}
		}
		return new ArrayList<>();
	}

	private Map<String, Integer> fetchExcelHeader(Row row) {
		Map<String, Integer> headersMap = new HashMap<>();
		short minColIx = row.getFirstCellNum();
		short maxColIx = row.getLastCellNum();
		for (short colIx = minColIx; colIx < maxColIx; colIx++) {
			Cell cell = row.getCell(colIx);
			headersMap.put(cell.getStringCellValue(), cell.getColumnIndex());
		}
		return headersMap;
	}

	private List<PositionDetailsPojo> createPositionDetailsPojo(int fileStartRowIndex, Sheet sheet,
			Map<String, Integer> headersMap) {
		Map<String, PositionDetailsPojo> positionsMap = new HashMap<>();
		for (int i = fileStartRowIndex + 1; i <= sheet.getLastRowNum(); i++) {
			PositionDetailsPojo position = new PositionDetailsPojo();
			Row dataRow = sheet.getRow(i);
			if (dataRow != null) {
				addPositionSystemFields(position, dataRow, headersMap);
				String ircCode = addPositionCustomFieldsAndGetUniqueRowidentifier(position, dataRow, headersMap);
				updatePositionNameAndSubDepartmentName(position, ircCode);
				if (!positionsMap.containsKey(ircCode)) {
					positionsMap.put(ircCode, position);
				}
			}
		}
		return new ArrayList<>(positionsMap.values());
	}

	private void updatePositionNameAndSubDepartmentName(PositionDetailsPojo position, String ircCode) {
		position.setPositionName(ircCode + SPACE_DASH_SPACE + position.getDepartmentName() + SPACE_DASH_SPACE
				+ position.getSubDepartmentName() + SPACE_DASH_SPACE + position.getPositionName() + SPACE_DASH_SPACE
				+ position.getGradeName() + SPACE_DASH_SPACE + position.getLocation());
		int index1 = position.getSubDepartmentName().indexOf('.');
		position.setSubDepartmentName(
				position.getSubDepartmentName().substring(index1 + 1, position.getSubDepartmentName().length()));
	}

	private void addPositionSystemFields(PositionDetailsPojo position, Row dataRow, Map<String, Integer> headersMap) {
		Map<String, String> positionFields = excelColumnMappingUtils.generatPositionFieldsMap();

		Cell positionNameCell = dataRow
				.getCell(headersMap.get(positionFields.get(ExcelColumnMappingConstants.positionName)));
		position.setPositionName(positionNameCell != null ? positionNameCell.getStringCellValue() : null);

		Cell locationCell = dataRow.getCell(headersMap.get(positionFields.get(ExcelColumnMappingConstants.location)));
		position.setLocation(locationCell != null ? locationCell.getStringCellValue() : null);

		Cell gradeCell = dataRow.getCell(headersMap.get(positionFields.get(ExcelColumnMappingConstants.grade)));
		position.setGradeName(gradeCell != null ? gradeCell.getStringCellValue() : null);

		Cell primarySkillsCell = dataRow.getCell(headersMap.get(positionFields.get(ExcelColumnMappingConstants.skill)));
		position.setPrimarySkills(primarySkillsCell != null ? primarySkillsCell.getStringCellValue() : null);

		Cell responsibilityCell = dataRow
				.getCell(headersMap.get(positionFields.get(ExcelColumnMappingConstants.detailedJobDescription)));
		position.setResponsibilities(responsibilityCell != null ? responsibilityCell.getStringCellValue() : null);

		setVacancyDetails(position, dataRow, headersMap, positionFields);
		setRequisitionerAndOwner(position, dataRow, headersMap, positionFields);
		setDegreeDetails(position, dataRow, headersMap, positionFields);
		setDepartLevelDetails(position, dataRow, headersMap, positionFields);
		position.setHireByDate(getHireByDate());
		position.setRequisitionApprovalTemplateId(requisitionConfigProperties.getRequisitionApprovalTemplateId());
		position.setMinimumExperienceYr(requisitionConfigProperties.getMinExp());
		position.setMaximumExperienceYr(requisitionConfigProperties.getMaxExp());
	}

	private void setDepartLevelDetails(PositionDetailsPojo position, Row dataRow, Map<String, Integer> headersMap,
			Map<String, String> positionFields) {
		Cell departmentCell = dataRow
				.getCell(headersMap.get(positionFields.get(ExcelColumnMappingConstants.department)));
		position.setDepartmentName(departmentCell != null ? departmentCell.getStringCellValue() : null);

		Cell subDepartmentCell = dataRow
				.getCell(headersMap.get(positionFields.get(ExcelColumnMappingConstants.subDepartment)));
		position.setSubDepartmentName(subDepartmentCell != null ? subDepartmentCell.getStringCellValue() : null);
	}

	private void setVacancyDetails(PositionDetailsPojo position, Row dataRow, Map<String, Integer> headersMap,
			Map<String, String> positionFields) {
		Cell vacanciesCell = dataRow.getCell(headersMap.get(positionFields.get(ExcelColumnMappingConstants.vacancy)));
		if (vacanciesCell != null) {
			if (vacanciesCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				position.setVacancies(String.valueOf(Double.valueOf(vacanciesCell.getNumericCellValue()).intValue()));
			} else {
				position.setVacancies(vacanciesCell.getStringCellValue());
			}
		}
		
		Cell typeOfVacancyCell = dataRow.getCell(headersMap.get(positionFields.get(ExcelColumnMappingConstants.vacancyType)));
		position.setTypeOfVacancy(typeOfVacancyCell != null ? typeOfVacancyCell.getStringCellValue() : null);

		Cell replacementForCell = dataRow.getCell(headersMap.get(positionFields.get(ExcelColumnMappingConstants.replacementFor)));
		position.setReplacementEmpCode(replacementForCell != null ? replacementForCell.getStringCellValue() : null);
	}

	private void setDegreeDetails(PositionDetailsPojo position, Row dataRow, Map<String, Integer> headersMap,
			Map<String, String> positionFields) {
		List<String> degreeNames = new ArrayList<>();
		Cell minQualCell = dataRow.getCell(headersMap.get(positionFields.get(ExcelColumnMappingConstants.minQualLevel)));
		Cell maxQualCell = dataRow.getCell(headersMap.get(positionFields.get(ExcelColumnMappingConstants.maxQualLevel)));
		degreeNames.add(minQualCell != null ? minQualCell.getStringCellValue() : null);
		degreeNames.add(maxQualCell != null ? maxQualCell.getStringCellValue() : null);
		position.setDegreeNames(degreeNames);
	}

	private void setRequisitionerAndOwner(PositionDetailsPojo position, Row dataRow, Map<String, Integer> headersMap,
			Map<String, String> positionFields) {
		Cell requisitionerCell = dataRow
				.getCell(headersMap.get(positionFields.get(ExcelColumnMappingConstants.requisitioner)));
		if (requisitionerCell != null) {
			if (requisitionerCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				position.setRequisitionerName(
						String.valueOf(Double.valueOf(requisitionerCell.getNumericCellValue()).intValue()));
			} else {
				position.setRequisitionerName(requisitionerCell.getStringCellValue());
			}
		}
		Cell ownerCell = dataRow.getCell(headersMap.get(positionFields.get(ExcelColumnMappingConstants.owner)));
		if (ownerCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			position.setPositionOwnerName(String.valueOf(Double.valueOf(ownerCell.getNumericCellValue()).intValue()));
		} else {
			position.setPositionOwnerName(ownerCell.getStringCellValue());
		}
	}

	private String getHireByDate() {
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, Integer.valueOf(requisitionConfigProperties.getHireByDate()));
		dt = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(DD_MM_YYYY);
		return sdf.format(dt);
	}

	private String addPositionCustomFieldsAndGetUniqueRowidentifier(PositionDetailsPojo position, Row dataRow,
			Map<String, Integer> headersMap) {
		Map<String, String> positionCustomFields = excelColumnMappingUtils.generatPositionCustomFieldsMap();
		List<String> customFieldKeyList = new ArrayList<>();
		Set<String> customFieldKeySet = positionCustomFields.keySet();
		customFieldKeyList.addAll(customFieldKeySet);
		List<CustomFields> customFields = new ArrayList<>();
		for (String customFieldKey : customFieldKeyList) {
			Cell cell = dataRow.getCell(headersMap.get(positionCustomFields.get(customFieldKey)));
			if (cell != null) {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING && StringUtils.isNotBlank(cell.getStringCellValue())) {
					customFields.add(getCustomField(cell, customFieldKey));
				} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					customFields.add(getCustomField(cell, customFieldKey));
				}
			}
		}
		position.setCustomFields(customFields);
		return getUniqueId(dataRow, headersMap, positionCustomFields).getStringCellValue();
	}

	private Cell getUniqueId(Row dataRow, Map<String, Integer> headersMap, Map<String, String> positionCustomFields) {
		return dataRow.getCell(headersMap.get(positionCustomFields.get(ExcelColumnMappingConstants.ircCode)));
	}

	private CustomFields getCustomField(Cell cell, String customFieldLabel) {
		CustomFields customField = new CustomFields();
		String[] fieldList = StringUtils.split(customFieldLabel, '.');
		customField.setName(fieldList[fieldList.length - 1]);
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			customField.setValue(String.valueOf(Double.valueOf(cell.getNumericCellValue()).intValue()));
		} else {
			customField.setValue(cell.getStringCellValue());
		}
		return customField;
	}

	
}
