package de.oberamsystems.sos;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.CommandLineRunner;

import de.oberamsystems.sos.model.DbObject;
import de.oberamsystems.sos.model.DbObjectRepository;
import de.oberamsystems.sos.model.MyHttpService;
import de.oberamsystems.sos.model.MyHttpServiceRepository;
import de.oberamsystems.sos.model.MyProcess;
import de.oberamsystems.sos.model.MyProcessRepository;
import de.oberamsystems.sos.model.MyService;
import de.oberamsystems.sos.model.MyServiceRepository;

@ExtendWith(MockitoExtension.class)
public class DatabaseInitializerTest {

    @InjectMocks
    private DatabaseInitializer initializer;

    @Mock
    private MyProcessRepository processRepo;

    @Mock
    private MyServiceRepository serviceRepo;

    @Mock
    private DbObjectRepository dbObjRepo;

    @Mock
    private MyHttpServiceRepository httpSvcRepo;

    @Test
    public void testInitProcs() throws Exception {
        CommandLineRunner runner = initializer.initProcs(processRepo);
        runner.run(new String[]{});
        verify(processRepo, times(1)).save(any(MyProcess.class));
    }

    @Test
    public void testInitSvcs() throws Exception {
        CommandLineRunner runner = initializer.initSvcs(serviceRepo);
        runner.run(new String[]{});
        verify(serviceRepo, times(2)).save(any(MyService.class));
    }

    @Test
    public void testInitDbObjs() throws Exception {
        CommandLineRunner runner = initializer.initDbObjs(dbObjRepo);
        runner.run(new String[]{});
        verify(dbObjRepo, times(2)).save(any(DbObject.class));
    }

    @Test
    public void testInitHttpSvcs() throws Exception {
        CommandLineRunner runner = initializer.initHttpSvcs(httpSvcRepo);
        runner.run(new String[]{});
        verify(httpSvcRepo, times(9)).save(any(MyHttpService.class));
    }
}
