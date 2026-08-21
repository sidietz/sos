package de.oberamsystems.sos;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import de.oberamsystems.sos.mail.EmailService;
import de.oberamsystems.sos.model.MyService;
import de.oberamsystems.sos.model.NotRunner;

@ExtendWith(MockitoExtension.class)
public class MasterSchedulerTest2 {

    @InjectMocks
    private MasterScheduler scheduler;

    @Mock
    private EmailService mailer;

    private static HttpServer server;

    @BeforeAll
    public static void setUpServer() throws Exception {
        server = HttpServer.create(new InetSocketAddress(30001), 0);
        server.createContext("/v0/msg", new HttpHandler() {
            @Override
            public void handle(HttpExchange t) throws IOException {
                String response = "OK";
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });
        server.setExecutor(null);
        server.start();
    }

    @AfterAll
    public static void tearDownServer() {
        if (server != null) {
            server.stop(0);
        }
    }

    @Test
    public void testPrivateMethods() throws Exception {
        NotRunner nr = new NotRunner();
        nr.setWatchable(new MyService("testSvc"));
        nr.setDuration(Duration.ofSeconds(65));
        
        // sendNrMail(NotRunner)
        ReflectionTestUtils.invokeMethod(scheduler, "sendNrMail", nr);
        
        // sendNrMail(List)
        ReflectionTestUtils.invokeMethod(scheduler, "sendNrMail", Arrays.asList(nr));
        ReflectionTestUtils.invokeMethod(scheduler, "sendNrMail", Collections.emptyList());
        
        // sendNrMail(List, List)
        ReflectionTestUtils.invokeMethod(scheduler, "sendNrMail", Arrays.asList(nr), Arrays.asList(nr));
        ReflectionTestUtils.invokeMethod(scheduler, "sendNrMail", Collections.emptyList(), Collections.emptyList());
        
        // sendNrMail(List, List, List)
        ReflectionTestUtils.invokeMethod(scheduler, "sendNrMail", Arrays.asList(nr), Arrays.asList(nr), Arrays.asList(nr));
        ReflectionTestUtils.invokeMethod(scheduler, "sendNrMail", Collections.emptyList(), Collections.emptyList(), Collections.emptyList());

        // sendTelegramNotification
        ReflectionTestUtils.invokeMethod(scheduler, "sendTelegramNotification", Arrays.asList(nr), Arrays.asList(nr), Arrays.asList(nr));
        ReflectionTestUtils.invokeMethod(scheduler, "sendTelegramNotification", Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        // To trigger branch conditions in sendTelegramNotification:
        ReflectionTestUtils.invokeMethod(scheduler, "sendTelegramNotification", Arrays.asList(nr), Collections.emptyList(), Collections.emptyList());

        // sendNotification (already hit by telegram, but hit directly)
        ReflectionTestUtils.invokeMethod(scheduler, "sendNotification", "test message");
        
        // printNotRunners
        ReflectionTestUtils.invokeMethod(MasterScheduler.class, "printNotRunners", Arrays.asList(nr));
    }
}
