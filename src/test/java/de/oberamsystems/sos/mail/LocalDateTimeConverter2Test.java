package de.oberamsystems.sos.mail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.core.convert.TypeDescriptor;

public class LocalDateTimeConverter2Test {

    @Test
    public void testLocalDateTimeConverter2() {
        LocalDateTimeConverter2 converter = new LocalDateTimeConverter2();
        LocalDateTime now = LocalDateTime.of(2026, 8, 20, 15, 30, 0);
        
        String result = (String) converter.convert(now, TypeDescriptor.valueOf(LocalDateTime.class), TypeDescriptor.valueOf(String.class));
        assertEquals("2026-08-20 15:30:00", result);
        
        assertTrue(converter.matches(TypeDescriptor.valueOf(LocalDateTime.class), TypeDescriptor.valueOf(String.class)));
        assertFalse(converter.matches(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(LocalDateTime.class)));
        
        assertTrue(LocalDateTimeConverter2.matches(LocalDateTime.class, String.class));
        assertFalse(LocalDateTimeConverter2.matches(String.class, LocalDateTime.class));
    }
}
