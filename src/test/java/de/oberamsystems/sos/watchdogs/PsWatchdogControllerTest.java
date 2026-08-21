package de.oberamsystems.sos.watchdogs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.oberamsystems.sos.model.MyProcess;
import de.oberamsystems.sos.model.MyProcessRepository;
import de.oberamsystems.sos.model.NotRunner;

@ExtendWith(MockitoExtension.class)
public class PsWatchdogControllerTest {

    @Mock
    private MyProcessRepository repo;

    @InjectMocks
    private PsWatchdogController controller;

    @Test
    public void testGetNotRunners() {
        MyProcess proc1 = new MyProcess("test1", "cmd1");
        proc1.setRunning(true);
        MyProcess proc2 = new MyProcess("test2", "cmd2");
        proc2.setRunning(false);
        
        when(repo.findAll()).thenReturn(Arrays.asList(proc1, proc2));
        
        List<NotRunner> notRunners = controller.getNotRunners();
        assertEquals(1, notRunners.size());
    }

    @Test
    public void testCheck() {
        MyProcess proc1 = new MyProcess("nonexistentprocess999", "cmd1");
        
        when(repo.findAll()).thenReturn(Arrays.asList(proc1));
        
        controller.check();
        
        verify(repo, times(1)).save(any(MyProcess.class));
    }
}
