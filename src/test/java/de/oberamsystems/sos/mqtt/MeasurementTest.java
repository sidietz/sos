package de.oberamsystems.sos.mqtt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class MeasurementTest {

    @Test
    public void testMeasurement() {
        Measurement m = new Measurement();
        m.setTemperature(20.5f);
        m.setHumidity(50.0f);
        
        LocalDateTime now = LocalDateTime.now();
        m.setDate(now);
        
        assertEquals(0, m.getId()); // defaults to 0
        assertEquals(20.5f, m.getTemperature(), 0.001);
        assertEquals(50.0f, m.getHumidity(), 0.001);
        assertEquals(now, m.getDate());
    }
}
