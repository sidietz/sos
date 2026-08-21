package de.oberamsystems.sos.watchdogs;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import de.oberamsystems.sos.model.MyService;

public class SystemdWatchdogTest {

    @Test
    public void testCheckFailsForNonexistentService() {
        MyService svc = new MyService("nonexistentservice999");
        
        SystemdWatchdog watchdog = new SystemdWatchdog(svc);
        watchdog.check();
        
        assertFalse(svc.isRunning());
    }
    
    // We can't easily test the success branch without a real systemd service or mocking systemctl
    // But since SystemdWatchdog only has 1 big try/catch, we probably covered most lines with the fail test
    // If we want to cover the `date = elems.get(5)` etc, we would need to mock systemctl.
}
