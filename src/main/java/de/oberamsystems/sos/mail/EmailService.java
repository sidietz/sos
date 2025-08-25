package de.oberamsystems.sos.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	private EmailServiceConfig emailServiceConfig;

    public void sendEmail(String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailServiceConfig.getFrom());
        message.setTo(emailServiceConfig.getTo());
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
