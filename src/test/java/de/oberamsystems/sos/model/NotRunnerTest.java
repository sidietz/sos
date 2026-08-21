package de.oberamsystems.sos.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class NotRunnerTest {

    @Test
    public void testNotRunner() {
        MyService svc = new MyService("testSvc");
        LocalDateTime now = LocalDateTime.now();
        Duration d = Duration.ofSeconds(10);
        
        NotRunner nr = new NotRunner(svc, now, d);
        
        assertEquals(svc, nr.getWatchable());
        assertEquals(now, nr.getNotRunningSince());
        assertEquals(d, nr.getDuration());
        
        NotRunner nr2 = new NotRunner();
        nr2.setWatchable(svc);
        nr2.setNotRunningSince(now);
        nr2.setDuration(d);
        
        assertEquals(svc, nr2.getWatchable());
        assertEquals(now, nr2.getNotRunningSince());
        assertEquals(d, nr2.getDuration());
        
        assertTrue(nr.equals(nr2));
        
        assertEquals("NotRunner[Watchable=Watchable[id=null, name='testSvc', kind='service'], name='', kind='']", nr.toString());
    }
}
