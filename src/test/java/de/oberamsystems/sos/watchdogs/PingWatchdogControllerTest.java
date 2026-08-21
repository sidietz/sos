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

import de.oberamsystems.sos.model.MyHttpService;
import de.oberamsystems.sos.model.MyHttpServiceRepository;
import de.oberamsystems.sos.model.NotRunner;

@ExtendWith(MockitoExtension.class)
public class PingWatchdogControllerTest {

    @Mock
    private MyHttpServiceRepository repo;

    @InjectMocks
    private PingWatchdogController controller;

    @Test
    public void testGetNotRunners() {
        MyHttpService svc1 = new MyHttpService();
        svc1.setRunning(true);
        MyHttpService svc2 = new MyHttpService();
        svc2.setRunning(false);
        
        when(repo.findAll()).thenReturn(Arrays.asList(svc1, svc2));
        
        List<NotRunner> notRunners = controller.getNotRunners();
        assertEquals(1, notRunners.size());
    }

    @Test
    public void testCheck() {
        MyHttpService svc1 = new MyHttpService();
        svc1.setHttp("http://");
        svc1.setHostname("localhost");
        svc1.setPort(9999);
        
        when(repo.findAll()).thenReturn(Arrays.asList(svc1));
        
        controller.check();
        
        verify(repo, times(1)).save(any(MyHttpService.class));
    }
}
