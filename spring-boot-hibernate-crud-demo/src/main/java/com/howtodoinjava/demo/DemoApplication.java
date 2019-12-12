package com.howtodoinjava.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.WebApplicationContext;

import com.howtodoinjava.nk.CassRepoAndServiceLoaderFactory;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer{
	public static ConfigurableApplicationContext ctx;
	Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	
	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
		ctx = SpringApplication.run(DemoApplication.class, args);
		while(ctx==null) {
			System.out.println("wating for app to start");
		}
	    System.out.println("*****************************************************************************************************");
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		String configLocation = System.getProperty("global.appconf.dir"); //get the default config directory location
//	    String configPath = configLocation + File.separator + "springApplication"  + File.separator + "application.yml"; //set the configpath of this application instance exclusively
	    logger.info("Configpath: " + configLocation);
	    logger.info("Starting to run Spring boot app...");

		return builder.sources(this.getClass());
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// Logger initialization is deferred in case an ordered
		// LogServletContextInitializer is being used
		WebApplicationContext rootAppContext = createRootApplicationContext(
				servletContext);
		if (rootAppContext == null) {
			this.logger.debug("No ContextLoaderListener registered, as createRootApplicationContext() did not return an application context");
			throw new RuntimeException("No ContextLoaderListener registered, as createRootApplicationContext() did not return an application context");
		}
		CassRepoAndServiceLoaderFactory.launchFactory((ConfigurableApplicationContext) rootAppContext);
	}
	
	 @Bean
	  public ServletListenerRegistrationBean<ServletContextListener> listenerRegistrationBean() {
	    ServletListenerRegistrationBean<ServletContextListener> bean = 
	        new ServletListenerRegistrationBean<>();
	    bean.setListener(new Testing());
	    return bean;

	  }
}
