package de.oberamsystems.sos.watchdogs;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import de.oberamsystems.sos.model.MyHttpService;

public class PingWatchdogTest {

    private static HttpServer server;

    @BeforeAll
    public static void setUpServer() throws Exception {
        server = HttpServer.create(new InetSocketAddress(8888), 0);
        server.createContext("/", new HttpHandler() {
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
    public void testCheckSuccess() {
        MyHttpService svc = new MyHttpService();
        svc.setHttp("http://");
        svc.setHostname("127.0.0.1");
        svc.setPort(8888);
        
        PingWatchdog watchdog = new PingWatchdog(svc);
        watchdog.check();
        
        assertTrue(svc.isRunning());
    }

    @Test
    public void testCheckFailsForInvalidUrl() {
        MyHttpService svc = new MyHttpService();
        svc.setHttp("http://");
        svc.setHostname("localhost");
        svc.setPort(9999);
        
        PingWatchdog watchdog = new PingWatchdog(svc);
        watchdog.check();
        
        assertFalse(svc.isRunning());
    }
}
