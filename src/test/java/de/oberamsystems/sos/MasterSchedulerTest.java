package de.oberamsystems.sos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import de.oberamsystems.sos.mail.EmailService;
import de.oberamsystems.sos.model.DbObjectRepository;
import de.oberamsystems.sos.model.MyHttpServiceRepository;
import de.oberamsystems.sos.model.MyProcess;
import de.oberamsystems.sos.model.MyProcessRepository;
import de.oberamsystems.sos.model.MyService;
import de.oberamsystems.sos.model.MyServiceRepository;
import de.oberamsystems.sos.model.NotRunner;
import de.oberamsystems.sos.model.NotRunnerManager;
import de.oberamsystems.sos.model.Watchable;

@ExtendWith(MockitoExtension.class)
public class MasterSchedulerTest {

    @Mock
    private MyProcessRepository procRepo;

    @Mock
    private MyServiceRepository serviceRepo;

    @Mock
    private DbObjectRepository dbObjectRepo;

    @Mock
    private MyHttpServiceRepository httpRepo;

    @Mock
    private NotRunnerManager notRunnerService;

    @Mock
    private EmailService mailer;

    @InjectMocks
    private MasterScheduler masterScheduler;

    private Watchable watchable1;
    private Watchable watchable2;
    private NotRunner notRunner1;
    private NotRunner notRunner2;

    @BeforeEach
    void setUp() {
        watchable1 = new MyProcess("w1", "command1");
        watchable1.setId(1L);

        watchable2 = new MyService("w2");
        watchable2.setId(2L);

        notRunner1 = new NotRunner(watchable1, LocalDateTime.now(), Duration.ZERO);
        notRunner2 = new NotRunner(watchable2, LocalDateTime.now(), Duration.ZERO);
    }

    @Test
    void testWentNotRunning() {
        List<NotRunner> oldNr = new ArrayList<>();
        List<NotRunner> newNr = Arrays.asList(notRunner1);

        List<NotRunner> result = MasterScheduler.wentNotRunning(oldNr, newNr);

        assertEquals(1, result.size());
        assertEquals(notRunner1, result.get(0));
    }

    @Test
    void testWentNotRunning_alreadyNotRunning() {
        List<NotRunner> oldNr = Arrays.asList(notRunner1);
        List<NotRunner> newNr = Arrays.asList(notRunner1);

        List<NotRunner> result = MasterScheduler.wentNotRunning(oldNr, newNr);

        assertTrue(result.isEmpty());
    }

    @Test
    void testWentRunning() {
        List<NotRunner> oldNr = Arrays.asList(notRunner1);
        List<NotRunner> newNr = new ArrayList<>();

        List<NotRunner> result = MasterScheduler.wentRunning(oldNr, newNr);

        assertEquals(1, result.size());
        assertEquals(notRunner1, result.get(0));
    }

    @Test
    void testWentRunning_stillNotRunning() {
        List<NotRunner> oldNr = Arrays.asList(notRunner1);
        List<NotRunner> newNr = Arrays.asList(notRunner1);

        List<NotRunner> result = MasterScheduler.wentRunning(oldNr, newNr);

        assertTrue(result.isEmpty());
    }

    @Test
    void testScheduleTest_Empty() {
        when(notRunnerService.getNotRunners()).thenReturn(new ArrayList<>());
        
        // Mock empty repositories to avoid NullPointerExceptions in watchdogs
        when(procRepo.findAll()).thenReturn(new ArrayList<>());
        when(serviceRepo.findAll()).thenReturn(new ArrayList<>());
        when(dbObjectRepo.getByName(anyString())).thenReturn(Arrays.asList(new de.oberamsystems.sos.model.DbObject("dummy")));
        when(httpRepo.findAll()).thenReturn(new ArrayList<>());

        // We also need to avoid exceptions when db checks fail, DbObjectController attempts checkDb which will connect to localhost pg database. 
        // But if checkDb is called, it might fail or return false, which is fine, it just logs and sets running to false.
        
        int result = masterScheduler.scheduleTest();

        assertEquals(0, result);
        org.mockito.Mockito.verify(notRunnerService, org.mockito.Mockito.times(2)).getNotRunners();
    }
}
