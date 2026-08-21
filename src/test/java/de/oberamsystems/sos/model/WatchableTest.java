package de.oberamsystems.sos.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.Test;

public class WatchableTest {

    private static class TestWatchable extends Watchable {
        public TestWatchable(String name, String kind) {
            super(name, kind);
        }
    }

    @Test
    public void testWatchableProperties() {
        TestWatchable w = new TestWatchable("test", "testKind");
        w.setId(1L);
        w.setRuntime(Duration.ofSeconds(10));
        w.setRuntime2("10s");
        w.setRunning(true);
        
        assertEquals(1L, w.getId());
        assertEquals("test", w.getName());
        assertEquals("testKind", w.getKind());
        assertEquals(Duration.ofSeconds(10), w.getRuntime());
        assertEquals("10s", w.getRuntime2());
        assertTrue(w.isRunning());
        
        w.setName("newTest");
        assertEquals("newTest", w.getName());
    }
    
    @Test
    public void testEquals() {
        TestWatchable w1 = new TestWatchable("test1", "kind1");
        w1.setId(1L);
        TestWatchable w2 = new TestWatchable("test2", "kind2");
        w2.setId(1L);
        TestWatchable w3 = new TestWatchable("test3", "kind3");
        w3.setId(2L);
        
        assertTrue(w1.equals(w2));
        assertFalse(w1.equals(w3));
        assertFalse(w1.equals(null));
        assertFalse(w1.equals(new Object()));
    }
    
    @Test
    public void testToString() {
        TestWatchable w = new TestWatchable("test", "kind");
        w.setId(1L);
        assertEquals("Watchable[id=1, name='test', kind='kind']", w.toString());
    }
}
