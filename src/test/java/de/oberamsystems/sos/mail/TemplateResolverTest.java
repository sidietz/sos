package de.oberamsystems.sos.mail;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

public class TemplateResolverTest {

    @Test
    public void testThymeleafTemplateResolver() {
        TemplateResolver tr = new TemplateResolver();
        ITemplateResolver resolver = tr.thymeleafTemplateResolver();
        
        assertNotNull(resolver);
        assertEquals("HTML", ((ClassLoaderTemplateResolver) resolver).getTemplateMode().toString());
    }

    @Test
    public void testThymeleafTemplateEngine() {
        TemplateResolver tr = new TemplateResolver();
        ITemplateResolver resolver = tr.thymeleafTemplateResolver();
        SpringTemplateEngine engine = tr.thymeleafTemplateEngine(resolver);
        
        assertNotNull(engine);
    }
}
