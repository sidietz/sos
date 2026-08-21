package de.oberamsystems.sos.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotRunnerManagerTest {

    private NotRunnerManager manager;

    @BeforeEach
    public void setup() {
        manager = new NotRunnerManager();
    }

    @Test
    public void testAddAndGet() {
        NotRunner nr = new NotRunner(new MyService("test"), null, null);
        manager.addNotRunner(nr);
        
        List<NotRunner> runners = manager.getNotRunners();
        assertEquals(1, runners.size());
        assertEquals(nr, runners.get(0));
    }

    @Test
    public void testDelete() {
        NotRunner nr = new NotRunner(new MyService("test"), null, null);
        manager.addNotRunner(nr);
        manager.deleteNotRunnerById(nr);
        
        assertTrue(manager.getNotRunners().isEmpty());
    }

    @Test
    public void testClear() {
        manager.addNotRunner(new NotRunner(new MyService("test1"), null, null));
        manager.addNotRunner(new NotRunner(new MyService("test2"), null, null));
        manager.clear();
        
        assertTrue(manager.getNotRunners().isEmpty());
    }
    
    @Test
    public void testSetNotRunners() {
        List<NotRunner> runners = Arrays.asList(
            new NotRunner(new MyService("test1"), null, null),
            new NotRunner(new MyService("test2"), null, null)
        );
        manager.setNotRunners(runners);
        assertEquals(2, manager.getNotRunners().size());
    }
}
