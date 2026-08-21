package de.oberamsystems.sos.watchdogs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.oberamsystems.sos.model.MyProcess;

public class PsWatchdogTest {

    @Test
    public void testCheckSuccess() {
        // Find java process which is running our tests
        MyProcess proc = new MyProcess("java", "java"); // We can pass just name which will be shellCommand
        
        PsWatchdog watchdog = new PsWatchdog(proc);
        watchdog.check();
        
        // As long as there is a java process, this should succeed and set running=true
        // If grep returns multiple, the code uses lines.getFirst() and parses it.
        // It should not throw an exception.
        assertTrue(proc.isRunning());
    }

    @Test
    public void testCheckFailsForNonexistentProcess() {
        MyProcess proc = new MyProcess("nonexistentprocess999", "nonexistentprocess999");
        
        PsWatchdog watchdog = new PsWatchdog(proc);
        watchdog.check();
        
        assertFalse(proc.isRunning());
    }
}
