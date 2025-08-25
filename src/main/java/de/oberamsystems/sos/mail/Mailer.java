package de.oberamsystems.sos.mail;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Deprecated
@Service
@PropertySource("classpath:mail.properties")
public class Mailer {

	@Value("${username}")
	private String username;

	@Value("${password}")
	private String password;

	Message message;

	public Mailer() {

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "mail.gmx.net");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.ssl.trust", "mail.gmx.net");

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		message = new MimeMessage(session);
	}

	public void send(String category, String name) {
		
		try {
			message.setFrom(new InternetAddress("sdietz101@gmx.de"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("simon.dietz@vodafone.de"));
			message.setSubject(String.format("'%s' '%s' läuft nicht", category, name));

			String msg = String.format("'%s' '%s' läuft nicht", category, name);

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);

			message.setContent(multipart);

			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
