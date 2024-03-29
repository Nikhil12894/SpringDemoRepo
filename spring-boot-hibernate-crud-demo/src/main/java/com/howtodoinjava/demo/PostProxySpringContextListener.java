package com.howtodoinjava.demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.google.gson.Gson;
import com.howtodoinjava.nk.PostProxy;

public class PostProxySpringContextListener implements ApplicationListener<ContextRefreshedEvent> {

private static Logger LOG = LoggerFactory.getLogger(PostProxySpringContextListener.class);

@Override
public void onApplicationEvent(ContextRefreshedEvent event) {
    ApplicationContext context = event.getApplicationContext();
    Gson gson = new Gson();
    System.out.println("*****************************************************************************************************");
    System.out.println(gson.toJson(context));
    String[] beanDefinitionNames = context.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
        String originalClassName = getOriginalClassName(beanDefinitionName, event);
        if (originalClassName != null) {
            invokeAnnotatedMethods(context, beanDefinitionName, originalClassName);
        }
    }
}

private String getOriginalClassName(String name, ContextRefreshedEvent event) {
    try {
        ConfigurableListableBeanFactory factory =
                (ConfigurableListableBeanFactory)event.getApplicationContext().getAutowireCapableBeanFactory();
        BeanDefinition beanDefinition = factory.getBeanDefinition(name);
        return beanDefinition.getBeanClassName();
    } catch (NoSuchBeanDefinitionException e) {
        LOG.debug("Can't get bean definition for : " + name);
        return null;
    }
}

private void invokeAnnotatedMethods(ApplicationContext context, String beanDefinitionName, String originalClassName) {
    try {
        Class<?> originalClass = Class.forName(originalClassName);
        Method[] methods = originalClass.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(PostProxy.class)) {
                LOG.info("Executing @PostProxy annotated initialization method: " + method.toString());
                Object bean = context.getBean(beanDefinitionName);
                Method currentMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
                currentMethod.invoke(bean);
            }
        }
    } catch (ClassNotFoundException e) {
        LOG.trace("No class instance for bean " + beanDefinitionName + " with class name " + originalClassName);
    } catch (NoSuchMethodException e) {
        LOG.error("Error finding @PostProxy method for bean " + beanDefinitionName, e);
    } catch (InvocationTargetException e) {
        LOG.error("Error invoking @PostProxy method for bean " + beanDefinitionName, e);
    } catch (IllegalAccessException e) {
        LOG.error("Can't invoke annotated method in bean" + beanDefinitionName + " with class name " + originalClassName
                + ". Please check access modifiers on @PostProxy methods.", e);
    }
}
}