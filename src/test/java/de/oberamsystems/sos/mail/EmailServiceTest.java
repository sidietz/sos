package de.oberamsystems.sos.mail;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import de.oberamsystems.sos.model.NotRunner;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private EmailServiceConfig emailServiceConfig;

    @Mock
    private TemplateEngine templateEngine;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    public void setup() {
        when(emailServiceConfig.getFrom()).thenReturn("from@example.com");
        when(emailServiceConfig.getTo()).thenReturn("to@example.com");
    }

    @Test
    public void testSendEmail() {
        emailService.sendEmail("Test Subject", "Test Body");
        // sendEmail method does not actually call mailSender.send() in the code (it's commented out)
    }

    @Test
    public void testSendHtmlEmail1() {
        MimeMessage mimeMessage = new MimeMessage((Session) null);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(templateEngine.process(any(String.class), any(Context.class))).thenReturn("<html>Test</html>");

        emailService.sendHtmlEmail("Test Subject", new ArrayList<NotRunner>());

        verify(mailSender, times(1)).send(mimeMessage);
    }
    
    @Test
    public void testSendHtmlEmail2() {
        MimeMessage mimeMessage = new MimeMessage((Session) null);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(templateEngine.process(any(String.class), any(Context.class))).thenReturn("<html>Test</html>");

        emailService.sendHtmlEmail("Test Subject", new ArrayList<NotRunner>(), new ArrayList<NotRunner>());

        verify(mailSender, times(1)).send(mimeMessage);
    }
    
    @Test
    public void testSendHtmlEmail3() {
        MimeMessage mimeMessage = new MimeMessage((Session) null);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(templateEngine.process(any(String.class), any(Context.class))).thenReturn("<html>Test</html>");

        emailService.sendHtmlEmail("Test Subject", new ArrayList<NotRunner>(), new ArrayList<NotRunner>(), new ArrayList<NotRunner>());

        verify(mailSender, times(1)).send(mimeMessage);
    }
}
