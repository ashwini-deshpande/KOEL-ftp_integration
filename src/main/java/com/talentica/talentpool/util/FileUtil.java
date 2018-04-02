package com.talentica.talentpool.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.talentica.talentpool.RequisitionConfigProperties;
import com.talentica.talentpool.models.PositionErrorDTO;

@Component
public class FileUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	private static final String FILE_HEADER = "IRCCode, ErrorMessage";


	@Autowired
	private RequisitionConfigProperties requisitionConfigProperties;

	public File getLatestFilefromDir() {
		String dirPath = requisitionConfigProperties.getPositionFilePath();
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}
		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		LOGGER.info(String.format("lastModifiedFile in dir path : %s  file : %s", dirPath, lastModifiedFile));
		return lastModifiedFile;
	}

	public File moveFiles(List<PositionErrorDTO> positionErrorDTOList) {
		
		String fileName = "summary.csv";
		File f = getLatestFilefromDir();
		String dirPath = requisitionConfigProperties.getProcessedExcelsPath();
		boolean created = false;
		String name = getFileNameWithoutExtension(f);
		File processedExcel = new File(dirPath + File.separator + f.getName());
		try {
			
			Files.copy(f.toPath(), processedExcel.toPath(),StandardCopyOption.REPLACE_EXISTING);
			f.delete();
			if(!CollectionUtils.isEmpty(positionErrorDTOList)){
	        	File file = new File(dirPath + File.separator + name + "_" + fileName);
	        	created = file.createNewFile();
	        	writeFile(positionErrorDTOList, file, created);
	        	return file;
			}
		} catch (IOException e) {
			LOGGER.debug(
					"Exception occured while moving xls file from one location to another or creating summary file : ",
					e);
		}
		return null;
	}

	private String getFileNameWithoutExtension(File f) {
		int pos = f.getName().lastIndexOf('.');
		String name = null;
		if (pos > 0) {
			name = f.getName().substring(0, pos);
		}
		return name;
	}

	private void writeFile(List<PositionErrorDTO> positionErrorDTOList, File file, boolean created) {
		FileWriter fileWriter = null;
		try {
			if (created) {
				fileWriter = new FileWriter(file);
				fileWriter.append(FILE_HEADER);
				fileWriter.append(NEW_LINE_SEPARATOR);
				for (PositionErrorDTO positionErrorDTO : positionErrorDTOList) {
					fileWriter.append(positionErrorDTO.getPositionIRCCode());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(positionErrorDTO.getErrorMsg());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(NEW_LINE_SEPARATOR);
				}
			}
		} catch (IOException e) {
			LOGGER.debug("Exception occured while writing summary csv file : ", e);
		}finally {
	        try {
				if (fileWriter != null) {
					fileWriter.flush();
					fileWriter.close();
				}
		    } catch (IOException e) {
		    	LOGGER.debug("Error while flushing/closing fileWriter !!!",e);
		    }
	   }
	}
}
