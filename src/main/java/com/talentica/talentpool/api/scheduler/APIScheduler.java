package com.talentica.talentpool.api.scheduler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.talentica.talentpool.email.notification.NotifyUserPositionCreationStatus;
import com.talentica.talentpool.excel.position.creation.ExcelPositionCreation;
import com.talentica.talentpool.models.PositionDetailsPojo;
import com.talentica.talentpool.models.PositionErrorDTO;
import com.talentica.talentpool.util.FileUtil;

@Component
public class APIScheduler {

	private static final Logger LOGGER = LoggerFactory.getLogger(APIScheduler.class);

	private Environment env;

	private ExcelPositionCreation excelPositionCreation;

	private NotifyUserPositionCreationStatus notifyUserPositionCreationStatus;

	private FileUtil fileUtil;

	@Autowired
	public APIScheduler(Environment env, ExcelPositionCreation excelPositionCreation,
			NotifyUserPositionCreationStatus notifyUserPositionCreationStatus, FileUtil fileUtil) {
		this.env = env;
		this.excelPositionCreation = excelPositionCreation;
		this.notifyUserPositionCreationStatus = notifyUserPositionCreationStatus;
		this.fileUtil = fileUtil;
	}

	@Scheduled(cron = "${position.creation.job.cron}")
	public void positionCreationSchedulerJob() {
		try {
			if ("true".equals(env.getProperty("scheduler.active"))) {
				String appUrl = env.getProperty("talentpool-api.url");
				String authToken = getAuthToken(appUrl);
				if (!StringUtils.isEmpty(authToken)) {
					createPositions(appUrl, authToken);
				}	
			}

		} catch (Exception e) {
			LOGGER.error("Error while processing position creation schedular", e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String getAuthToken(String appUrl) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			JSONObject json = new JSONObject();
			json.put("userName", env.getProperty("talentpool-api.username"));
			json.put("password", env.getProperty("talentpool-api.password"));
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity entity = new HttpEntity(json.toString(), headers);
			HttpEntity<String> response = restTemplate.exchange(appUrl.concat("/rest/user/login"), HttpMethod.POST,
					entity, String.class);
			JSONObject jsonObject = new JSONObject(response.getBody());
			return jsonObject.get("tokenId").toString();
		} catch (Exception e) {
			LOGGER.debug("Error occured while calling rest api for login : ", e);
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void createPositions(String appUrl, String authToken) {
		try {
			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("AUTH_TOKEN", authToken);
			List<PositionDetailsPojo> positionDetailsPojoList = excelPositionCreation
					.getPositionDetaislPojoListFromExcel();
			List<PositionErrorDTO> positionErrorDTOList = new ArrayList<>();
			if (!CollectionUtils.isEmpty(positionDetailsPojoList)) {
				LOGGER.info("position creation schedular started");
				for (PositionDetailsPojo positionDetailsPojo : positionDetailsPojoList) {
					HttpEntity entity = new HttpEntity(positionDetailsPojo, headers);
					try {
						restTemplate.exchange(appUrl.concat("/rest/position/excel-creation"), HttpMethod.POST, entity,
								String.class);
					} catch (HttpClientErrorException e) {
						LOGGER.debug("Position creation call failed for position with Name : "
								+ positionDetailsPojo.getPositionName());
						PositionErrorDTO positionErrorDTO = new PositionErrorDTO();
						positionErrorDTO.setPositionIRCCode(getPositionIRCCode(positionDetailsPojo));
						positionErrorDTO.setErrorMsg(e.getResponseBodyAsString());
						positionErrorDTOList.add(positionErrorDTO);
					} catch (Exception e) {
						LOGGER.debug("Position creation call failed for position with Name : "
								+ positionDetailsPojo.getPositionName());
						PositionErrorDTO positionErrorDTO = new PositionErrorDTO();
						positionErrorDTO.setPositionIRCCode(getPositionIRCCode(positionDetailsPojo));
						positionErrorDTO.setErrorMsg(e.getMessage());
						positionErrorDTOList.add(positionErrorDTO);
					}
				}
				File summaryFile = moveFiles(positionErrorDTOList);
				if (summaryFile != null) {
					sendEmailNotification(summaryFile);
				}
				LOGGER.info("position creation schedular completed");
			}
			
		} catch (Exception e) {
			LOGGER.debug("Error occured while position creation in scheduler : ", e);
		}
	}

	private String getPositionIRCCode(PositionDetailsPojo positionDetailsPojo) {
		String[] positionNameAttr = positionDetailsPojo.getPositionName().split("-");
		return positionNameAttr[0];
	}

	private File moveFiles(List<PositionErrorDTO> positionErrorDTOList) {
		return fileUtil.moveFiles(positionErrorDTOList);
	}

	private void sendEmailNotification(File summaryFile) {
		notifyUserPositionCreationStatus.sendEmail(summaryFile);
	}
}
