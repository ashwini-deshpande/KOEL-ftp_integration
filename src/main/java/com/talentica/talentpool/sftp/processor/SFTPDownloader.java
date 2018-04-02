package com.talentica.talentpool.sftp.processor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

@Component
public class SFTPDownloader {

	private static final Logger LOGGER = LoggerFactory.getLogger(SFTPDownloader.class);

	private final Environment env;

	@Autowired
	public SFTPDownloader(Environment env) {
		this.env = env;
	}

	@Scheduled(cron = "${ftp.downloader.job.cron}")
	public void sftpFileDownloaderJob() {
		if ("true".equals(env.getProperty("ftp.active"))) {
			String sftpHost = env.getProperty("ftp.host");
			int sftpPort = Integer.parseInt(env.getProperty("ftp.port"));
			String sftpUser = env.getProperty("ftp.username");
			String sftpPass = env.getProperty("ftp.password");
			String sftpFromRemoteDirectory = env.getProperty("ftp.from.remote.directory");
			String sftpToRemoteDirectory = env.getProperty("ftp.to.remote.directory");
			String localFile = env.getProperty("ftp.local.directory");

			Session session = null;
			Channel channel = null;
			ChannelSftp channelSftp = null;

			try {
				JSch jsch = new JSch();
				session = jsch.getSession(sftpUser, sftpHost, sftpPort);
				session.setPassword(sftpPass);
				java.util.Properties config = new java.util.Properties();
				config.put("StrictHostKeyChecking", "no");
				session.setConfig(config);
				session.connect();
				channel = session.openChannel("sftp");
				channel.connect();
				channelSftp = (ChannelSftp) channel;
				channelSftp.cd(sftpFromRemoteDirectory);
				byte[] buffer = new byte[1024];
				String lastModifiedFile = getLastModifiedFile(channelSftp.ls(sftpFromRemoteDirectory));
				dowloadFileFromRemote(localFile, channelSftp, buffer, lastModifiedFile);
				acknowledgeAndDeleteRemoteFile(sftpFromRemoteDirectory, sftpToRemoteDirectory, localFile, channelSftp,
						lastModifiedFile);

			} catch (Exception ex) {
				LOGGER.error("Exception occured while downlaoding file from FTP server", ex);
			}

		}
	}

	private void acknowledgeAndDeleteRemoteFile(String sftpFromRemoteDirectory, String sftpToRemoteDirectory,
			String localFile, ChannelSftp channelSftp, String lastModifiedFile)
			throws SftpException, FileNotFoundException {
		channelSftp.cd(sftpToRemoteDirectory);
		File f = new File(localFile + lastModifiedFile);
		channelSftp.put(new FileInputStream(f), f.getName());
		channelSftp.cd(sftpFromRemoteDirectory);
		channelSftp.rm(lastModifiedFile);
		LOGGER.info("Latest file Uploaded to To_client Folder And Deleted from From_client Folder.");
	}

	private void dowloadFileFromRemote(String localFile, ChannelSftp channelSftp, byte[] buffer,
			String lastModifiedFile) {
		LOGGER.info("Latest file Name: " + lastModifiedFile);
		if (StringUtils.isNotBlank(lastModifiedFile)) {
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			OutputStream os = null;
			try {
				bis = new BufferedInputStream(channelSftp.get(lastModifiedFile));
				File newFile = new File(localFile + File.separator + lastModifiedFile);
				os = new FileOutputStream(newFile);
				bos = new BufferedOutputStream(os);
				int readCount;
				while ((readCount = bis.read(buffer)) > 0) {
					bos.write(buffer, 0, readCount);
				}
			} catch (Exception e) {
				LOGGER.error("Exception occured while downlaoding file dowloadFileFromRemote ", e);
			} finally {
				try {
					if (bis != null) {
						bis.close();
					}
					if (bos != null) {
						bos.close();
					}
					if (os != null) {
						os.close();
					}
				} catch (Exception e) {
					LOGGER.error("Exception occured while closing resources ", e);
				}
			}

		}
	}

	private String getLastModifiedFile(Vector list) {
		int currentLatestTime = 0;
		int nextTime = 0;
		ChannelSftp.LsEntry lsEntry = null;
		SftpATTRS sftpAttrs = null;
		String nextName = null;
		String latestFile = null;
		try {
			if (list.isEmpty()) {
				return null;
			} else {
				for (Object sftpFile : list) {
					lsEntry = (ChannelSftp.LsEntry) sftpFile;
					nextName = lsEntry.getFilename();
					sftpAttrs = lsEntry.getAttrs();
					nextTime = sftpAttrs.getMTime();
					if (!sftpAttrs.isDir() && currentLatestTime < nextTime && nextName.length() > 3) {
						latestFile = nextName;
						currentLatestTime = nextTime;
					}
				}
				return latestFile;
			}
		} catch (Exception ex) {
			LOGGER.error("Exception occured while finding latest file on FTP server", ex);
		}
		return null;
	}

}