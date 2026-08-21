package de.oberamsystems.sos.mail;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ConversionConfigurationTest {

    @Test
    public void testConversionConfiguration() {
        ConversionConfiguration config = new ConversionConfiguration();
        assertNotNull(config.localDatetimeConverter());
    }
}
