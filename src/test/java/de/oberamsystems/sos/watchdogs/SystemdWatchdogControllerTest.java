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

import de.oberamsystems.sos.model.MyService;
import de.oberamsystems.sos.model.MyServiceRepository;
import de.oberamsystems.sos.model.NotRunner;

@ExtendWith(MockitoExtension.class)
public class SystemdWatchdogControllerTest {

    @Mock
    private MyServiceRepository repo;

    @InjectMocks
    private SystemdWatchdogController controller;

    @Test
    public void testGetNotRunners() {
        MyService svc1 = new MyService("test1");
        svc1.setRunning(true);
        MyService svc2 = new MyService("test2");
        svc2.setRunning(false);
        
        when(repo.findAll()).thenReturn(Arrays.asList(svc1, svc2));
        
        List<NotRunner> notRunners = controller.getNotRunners();
        assertEquals(1, notRunners.size());
    }

    @Test
    public void testCheck() {
        MyService svc1 = new MyService("nonexistentservice999");
        
        when(repo.findAll()).thenReturn(Arrays.asList(svc1));
        
        controller.check();
        
        verify(repo, times(1)).save(any(MyService.class));
    }
}
