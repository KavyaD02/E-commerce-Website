package com.akshaya.ecommerce.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ViewResolverConfig {
	
	/*
	@Autowired
	WebApplicationContext webApplicationContext;

	@Bean
	public SpringResourceTemplateResolver thymeleafTemplateResolver(){
	    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	    templateResolver.setApplicationContext(webApplicationContext);
	    templateResolver.setOrder(9);
	    templateResolver.setPrefix("/WEB-INF/views/");
	    templateResolver.setSuffix("");
	    return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
	    SpringTemplateEngine springTemplateEngine= new SpringTemplateEngine();
	    springTemplateEngine.setTemplateResolver(thymeleafTemplateResolver());
	    springTemplateEngine.setEnableSpringELCompiler(true);
	    return springTemplateEngine;
	}*/
}
