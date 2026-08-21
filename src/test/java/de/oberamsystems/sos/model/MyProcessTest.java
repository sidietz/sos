package de.oberamsystems.sos.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MyProcessTest {

    @Test
    public void testMyProcess() {
        MyProcess p = new MyProcess("testName", "testCmd");
        assertEquals("testName", p.getName());
        
        p = new MyProcess();
        p.setName("test2");
        assertEquals("test2", p.getName());
    }
}
