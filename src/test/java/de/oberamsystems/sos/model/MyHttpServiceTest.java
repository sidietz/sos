package de.oberamsystems.sos.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class MyHttpServiceTest {

    @Test
    public void testConstructorsAndGettersSetters() {
        MyHttpService s1 = new MyHttpService(8080, "test1");
        assertEquals("test1", s1.getName());
        assertEquals("127.0.0.1", s1.getHostname());
        assertEquals(8080, s1.getPort());
        assertEquals("http://", s1.getHttp());
        
        MyHttpService s2 = new MyHttpService("host2", 9090, "test2", "host2_2", "https://");
        assertEquals("test2", s2.getName());
        assertEquals("host2", s2.getHostname());
        assertEquals("host2_2", s2.getHostingType());
        assertEquals(9090, s2.getPort());
        assertEquals("https://", s2.getHttp());
        
        MyHttpService s3 = new MyHttpService();
        s3.setHostname("host3");
        s3.setPort(8000);
        s3.setHttp("http://");
        
        assertEquals("host3", s3.getHostname());
        assertEquals(8000, s3.getPort());
        assertEquals("http://", s3.getHttp());
    }
}
