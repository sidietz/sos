package de.oberamsystems.sos.mail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.core.convert.TypeDescriptor;

public class LocalDateTimeConverterTest {

    @Test
    public void testConvert() {
        LocalDateTimeConverter converter = new LocalDateTimeConverter();
        LocalDateTime ldt = LocalDateTime.of(2026, 8, 20, 23, 14, 0);
        String result = converter.convert(ldt);
        assertEquals("2026-08-20 23:14:00", result);
    }
    
    @Test
    public void testMatches() {
        LocalDateTimeConverter converter = new LocalDateTimeConverter();
        TypeDescriptor source = TypeDescriptor.valueOf(LocalDateTime.class);
        TypeDescriptor target = TypeDescriptor.valueOf(String.class);
        assertTrue(converter.matches(source, target));
        
        TypeDescriptor falseSource = TypeDescriptor.valueOf(String.class);
        assertFalse(converter.matches(falseSource, target));
    }
    
    @Test
    public void testLdtConverterBean() {
        LocalDateTimeConverter converter = new LocalDateTimeConverter();
        assertNotNull(converter.ldtConverter());
    }
}
