package de.oberamsystems.sos.mail;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class EmailServiceConfigTest {

    @Test
    public void testEmailServiceConfig() {
        EmailServiceConfig config = new EmailServiceConfig();
        ReflectionTestUtils.setField(config, "from", "test@test.com");
        ReflectionTestUtils.setField(config, "to", "admin@test.com");
        
        assertNotNull(config.getFrom());
        assertNotNull(config.getTo());
    }
}
