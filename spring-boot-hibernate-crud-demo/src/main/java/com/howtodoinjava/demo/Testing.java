package com.howtodoinjava.demo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * Application Lifecycle Listener implementation class Testing
 *
 */
//@WebListener
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Testing implements ServletContextListener {
	Logger logger = LoggerFactory.getLogger(Testing.class);

    /**
     * Default constructor. 
     */
	
    public Testing() {
       this.logger.warn("*****************************************Testing()******************************************************   "+this.hashCode());
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
        this.logger.error("*****************************************Testing()  contextDestroyed******************************************************");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  {
    	System.err.println(sce.getServletContext().getServletContextName());
		
        this.logger.warn("*****************************************Testing()  contextInitialized******************************************************");
    }
	
}
