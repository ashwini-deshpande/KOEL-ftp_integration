package com.talentica.talentpool.email.notification;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class NotifyUserPositionCreationStatus {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotifyUserPositionCreationStatus.class);

	private Environment env;

	@Autowired
	public NotifyUserPositionCreationStatus(Environment env) {
		this.env = env;
	}

	public static void sendEmailWithAttachments(String host, String port, final String userName, final String password,
			String toAddressList, String subject, String message, String[] attachFiles) throws MessagingException {
		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);

		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		Session session = Session.getInstance(properties, auth);
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(userName));
		msg.addRecipients(Message.RecipientType.CC, InternetAddress.parse(toAddressList));

		msg.setSubject(subject);
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		if (attachFiles != null && attachFiles.length > 0) {
			for (String filePath : attachFiles) {
				MimeBodyPart attachPart = new MimeBodyPart();

				try {
					attachPart.attachFile(filePath);
				} catch (IOException ex) {
					LOGGER.info("Exception occured while sending email : " + ex);
				}
				multipart.addBodyPart(attachPart);
			}
		}
		msg.setContent(multipart);
		Transport.send(msg);
	}

	/**
	 * Test sending e-mail with attachments
	 */
	public void sendEmail(File summaryFile) {
		String host = env.getProperty("smtp.host");
		String port = env.getProperty("smtp.port");
		String mailFrom = env.getProperty("smtp.mailFrom");
		String password = env.getProperty("smtp.password");

		String mailTo = env.getProperty("smtp.mailTo");
		String subject = env.getProperty("smtp.subject");
		String message = env.getProperty("smtp.message");

		// attachments
		String[] attachFiles = new String[1];

		attachFiles[0] = summaryFile.getAbsolutePath();
		try {
			sendEmailWithAttachments(host, port, mailFrom, password, mailTo, subject, message, attachFiles);
			LOGGER.info("Email sent with attachment name : " + summaryFile.getName());
		} catch (Exception ex) {
			LOGGER.debug("Could not send email : ", ex);
		}
	}
}
