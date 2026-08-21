package de.oberamsystems.sos.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MyServiceTest {

    @Test
    public void testMyService() {
        MyService s = new MyService("testService");
        assertEquals("testService", s.getName());
        
        s = new MyService();
        s.setName("test2");
        assertEquals("test2", s.getName());
    }
}
