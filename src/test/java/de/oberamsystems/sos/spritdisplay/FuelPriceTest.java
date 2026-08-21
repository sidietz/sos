package de.oberamsystems.sos.spritdisplay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class FuelPriceTest {

    @Test
    public void testFuelPrice() {
        FuelPrice fp = new FuelPrice();
        fp.setE5(1.50f);
        fp.setE10(1.40f);
        fp.setDiesel(1.30f);
        
        LocalDateTime now = LocalDateTime.now();
        fp.setDate(now);
        
        assertEquals(0, fp.getId()); // defaults to 0
        assertEquals(1.50f, fp.getE5(), 0.001);
        assertEquals(1.40f, fp.getE10(), 0.001);
        assertEquals(1.30f, fp.getDiesel(), 0.001);
        assertEquals(now, fp.getDate());
    }
}
