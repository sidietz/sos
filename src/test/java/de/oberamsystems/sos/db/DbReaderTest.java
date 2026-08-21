package de.oberamsystems.sos.db;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

public class DbReaderTest {

    @Test
    public void testExecuteSuccess() throws Exception {
        // create memory db
        Class.forName("org.h2.Driver");
        Connection con = DriverManager.getConnection("jdbc:h2:mem:testdb2;DB_CLOSE_DELAY=-1", "sa", "");
        Statement stmt = con.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS test_table (id INT, name VARCHAR(255))");
        stmt.execute("INSERT INTO test_table (id, name) VALUES (1, 'test')");
        stmt.close();
        con.close();

        DbReader reader = new DbReader("org.h2.Driver", "jdbc:h2:mem:testdb2;DB_CLOSE_DELAY=-1", "sa", "");
        assertNotNull(reader.execute("SELECT * FROM test_table", Collections.emptyList()));
        assertNotNull(reader.execute("SELECT * FROM test_table WHERE name = ?", Arrays.asList("test")));
    }

    @Test
    public void testExecuteFailure() {
        DbReader reader = new DbReader("invalid.driver", "jdbc:invalid", "sa", "");
        assertNull(reader.execute("SELECT 1", Collections.emptyList()));
    }
}
