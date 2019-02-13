package jgb.dashboard;

import org.h2.server.web.*;
import org.springframework.boot.web.servlet.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	@Bean
	public ServletRegistrationBean<?> h2servletRegistration() {
        ServletRegistrationBean<?> registration 
            = new ServletRegistrationBean<>(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}
}