package de.oberamsystems.sos.mail;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

public class TemplateResolver {

	@Bean
	public ITemplateResolver thymeleafTemplateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
		//templateResolver.setPrefix("/mail/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML");
		templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
		return templateResolver;
	}
	
	@Bean
	public SpringTemplateEngine thymeleafTemplateEngine(ITemplateResolver templateResolver) {
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver);
	    //templateEngine.setTemplateEngineMessageSource(null);
	    return templateEngine;
	}

}
