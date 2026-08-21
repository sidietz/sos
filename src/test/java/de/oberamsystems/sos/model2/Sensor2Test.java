package de.oberamsystems.sos.model2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Sensor2Test {

    @Test
    public void testSensor2Properties() {
        Sensor2 s = new Sensor2();
        LocalDateTime now = LocalDateTime.now();
        s.setDate(now);
        s.setPlace("p1");
        s.setSensor("s1");
        s.setTemperature(20.5f);
        s.setHumidity(50.0f);
        s.setCo2(400.0f);
        
        assertEquals(now, s.getDate());
        assertEquals("p1", s.getPlace());
        assertEquals("s1", s.getSensor());
        assertEquals(20.5f, s.getTemperature());
        assertEquals(50.0f, s.getHumidity());
        assertEquals(400.0f, s.getCo2());
        
        assertEquals(String.format("Sensor2[id=0, date='%s', sensor='s1', co2='400.0']", now), s.toString());
    }

    @Test
    public void testBuild() throws Exception {
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(1);
        when(rs.getTimestamp("date")).thenReturn(Timestamp.valueOf(LocalDateTime.of(2026, 8, 20, 12, 0)));
        when(rs.getString("place")).thenReturn("p1");
        when(rs.getString("sensor")).thenReturn("s1");
        when(rs.getFloat("temperature")).thenReturn(20.5f);
        when(rs.getFloat("humidity")).thenReturn(50.0f);
        when(rs.getFloat("co2")).thenReturn(400.0f);
        
        Sensor2 s = new Sensor2();
        List<DbEntry> result = s.build(rs);
        
        assertEquals(1, result.size());
        Sensor2 s2 = (Sensor2) result.get(0);
        assertEquals("p1", s2.getPlace());
        assertEquals(20.5f, s2.getTemperature());
    }
}
