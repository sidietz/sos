package de.oberamsystems.sos.model2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PriceTest {

    @Test
    public void testPriceProperties() {
        Price p = new Price();
        LocalDateTime now = LocalDateTime.now();
        p.setDate(now);
        p.setStationId("st1");
        p.setDiesel(1.1f);
        p.setE5(1.2f);
        p.setE10(1.3f);
        
        assertEquals(now, p.getDate());
        assertEquals("st1", p.getStationId());
        assertEquals(1.1f, p.getDiesel());
        assertEquals(1.2f, p.getE5());
        assertEquals(1.3f, p.getE10());
        
        assertEquals(String.format("Price[date='%s', stationId='st1', e10='1.3']", now), p.toString());
    }

    @Test
    public void testBuild() throws Exception {
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getString("station_id")).thenReturn("st1");
        when(rs.getTimestamp("changed_at")).thenReturn(Timestamp.valueOf(LocalDateTime.of(2026, 8, 20, 12, 0)));
        when(rs.getFloat("diesel")).thenReturn(1.1f);
        when(rs.getFloat("e5")).thenReturn(1.2f);
        when(rs.getFloat("e10")).thenReturn(1.3f);
        
        Price p = new Price();
        List<DbEntry> result = p.build(rs);
        
        assertEquals(1, result.size());
        Price p2 = (Price) result.get(0);
        assertEquals("st1", p2.getStationId());
        assertEquals(1.1f, p2.getDiesel());
    }
}
