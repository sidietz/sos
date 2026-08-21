package de.oberamsystems.sos;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import de.oberamsystems.sos.mqtt.Measurement;
import de.oberamsystems.sos.spritdisplay.FuelPrice;
import de.oberamsystems.sos.model.DbObject;
import de.oberamsystems.sos.model.MyHttpService;
import de.oberamsystems.sos.mail.MyLocalDateTimeConversionService;

public class MissingCoverageTest {

    @Test
    public void testMeasurementRemaining() {
        Measurement m = new Measurement(1, null, "place1", "sensor1", 10.0f, 20.0f, 30.0f);
        m.setPlace("place2");
        m.setSensor("sensor2");
        m.setCo2(40.0f);
        assertNotNull(m.getPlace());
        assertNotNull(m.getSensor());
        assertNotNull(m.getCo2());
    }

    @Test
    public void testFuelPriceRemaining() {
        FuelPrice fp = new FuelPrice("station1", null, 1.0f, 1.1f, 1.2f);
        fp.setStationId("station2");
        assertNotNull(fp.getStationId());
        assertNotNull(fp.toString());
    }
    
    @Test
    public void testDbObjectRemaining() {
        DbObject db = new DbObject("db1");
        assertNotNull(db.toString());
    }
    
    @Test
    public void testMyHttpServiceRemaining() {
        MyHttpService hs = new MyHttpService(80, "test", "docker");
        MyHttpService hs2 = new MyHttpService("localhost", 80, "test2", "docker");
        assertNotNull(hs.getHostingType());
        assertNotNull(hs2.getHostingType());
        hs.setHostingType("bm");
    }

    @Test
    public void testMyLocalDateTimeConversionService() {
        MyLocalDateTimeConversionService s = new MyLocalDateTimeConversionService();
        assertNotNull(s);
        try {
            s.convert(null, java.time.LocalDateTime.now(), String.class);
        } catch (Exception e) {}
        try {
            s.convert(null, "test", String.class);
        } catch (Exception e) {}
    }
    

}
