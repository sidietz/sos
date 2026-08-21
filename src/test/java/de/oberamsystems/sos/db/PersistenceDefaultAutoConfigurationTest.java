package de.oberamsystems.sos.db;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;
import org.springframework.test.util.ReflectionTestUtils;

public class PersistenceDefaultAutoConfigurationTest {

    @Test
    public void testPersistenceDefaultAutoConfiguration() {
        PersistenceDefaultAutoConfiguration config = new PersistenceDefaultAutoConfiguration();
        Environment env = mock(Environment.class);
        when(env.getProperty("hibernate.hbm2ddl.auto")).thenReturn("update");
        when(env.getProperty("hibernate.dialect")).thenReturn("org.hibernate.dialect.H2Dialect");
        ReflectionTestUtils.setField(config, "env", env);
        
        assertNotNull(config.defaultDataSource());
        assertNotNull(config.defaultEntityManager());
        assertNotNull(config.defaultTransactionManager());
    }
}
