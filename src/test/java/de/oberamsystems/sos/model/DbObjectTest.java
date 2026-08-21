package de.oberamsystems.sos.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DbObjectTest {

    @Test
    public void testDbObject() {
        DbObject o = new DbObject("testDb");
        assertEquals("testDb", o.getName());
        
        o = new DbObject();
        o.setName("test2");
        assertEquals("test2", o.getName());
    }
}
