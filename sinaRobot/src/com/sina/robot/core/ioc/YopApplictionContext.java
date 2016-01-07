package com.sina.robot.core.ioc;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

public class YopApplictionContext implements ApplicationContext{

	private  ApplicationContext delegator;
	
	public YopApplictionContext(ApplicationContext delegator)
	{
		this.delegator=delegator;
	}
	
	@Override
	public Environment getEnvironment() {
		
		return this.delegator.getEnvironment();
	}

	@Override
	public boolean containsBeanDefinition(String arg0) {
		
		return this.delegator.containsBean(arg0);
	}

	@Override
	public <A extends Annotation> A findAnnotationOnBean(String arg0,
			Class<A> arg1) {
		return this.delegator.findAnnotationOnBean(arg0, arg1);
	}

	@Override
	public int getBeanDefinitionCount() {
		
		return this.delegator.getBeanDefinitionCount();
	}

	@Override
	public String[] getBeanDefinitionNames() {
		return this.delegator.getBeanDefinitionNames();
	}

	@Override
	public String[] getBeanNamesForType(Class<?> arg0) {
		
		return this.delegator.getBeanNamesForType(arg0);
	}

	@Override
	public String[] getBeanNamesForType(Class<?> arg0, boolean arg1,
			boolean arg2) {
		return this.delegator.getBeanNamesForType(arg0, arg1, arg2);
	}

	@Override
	public <T> Map<String, T> getBeansOfType(Class<T> arg0)
			throws BeansException {
		
		return this.delegator.getBeansOfType(arg0);
	}

	@Override
	public <T> Map<String, T> getBeansOfType(Class<T> arg0, boolean arg1,
			boolean arg2) throws BeansException {
		
		return this.delegator.getBeansOfType(arg0, arg1, arg2);
	}

	@Override
	public Map<String, Object> getBeansWithAnnotation(
			Class<? extends Annotation> arg0) throws BeansException {
		
		return this.delegator.getBeansWithAnnotation(arg0);
	}

	@Override
	public boolean containsBean(String arg0) {
		
		return this.delegator.containsBean(arg0);
	}

	@Override
	public String[] getAliases(String arg0) {
		return this.delegator.getAliases(arg0);
	}

	@Override
	public Object getBean(String arg0) throws BeansException {
		
		return this.delegator.getBean(arg0);
	}

	@Override
	public <T> T getBean(Class<T> arg0) throws BeansException {
		
		return this.delegator.getBean(arg0);
	}

	@Override
	public <T> T getBean(String arg0, Class<T> arg1) throws BeansException {
		
		return this.delegator.getBean(arg0, arg1);
	}

	@Override
	public Object getBean(String arg0, Object... arg1) throws BeansException {
		
		return this.delegator.getBean(arg0, arg1);
	}

	@Override
	public Class<?> getType(String arg0) throws NoSuchBeanDefinitionException {
		
		return this.delegator.getType(arg0);
	}

	@Override
	public boolean isPrototype(String arg0)
			throws NoSuchBeanDefinitionException {
		
		return this.delegator.isPrototype(arg0);
	}

	@Override
	public boolean isSingleton(String arg0)
			throws NoSuchBeanDefinitionException {
		
		return this.delegator.isSingleton(arg0);
	}

	@Override
	public boolean isTypeMatch(String arg0, Class<?> arg1)
			throws NoSuchBeanDefinitionException {
		
		return this.delegator.isTypeMatch(arg0, arg1);
	}

	@Override
	public boolean containsLocalBean(String arg0) {
		 
		return this.delegator.containsLocalBean(arg0);
	}

	@Override
	public BeanFactory getParentBeanFactory() {
		
		return this.delegator.getParentBeanFactory();
	}

	@Override
	public String getMessage(MessageSourceResolvable arg0, Locale arg1)
			throws NoSuchMessageException {
		 
		return this.delegator.getMessage(arg0, arg1);
	}

	@Override
	public String getMessage(String arg0, Object[] arg1, Locale arg2)
			throws NoSuchMessageException {
		 
		return this.delegator.getMessage(arg0, arg1, arg2);
	}

	@Override
	public String getMessage(String arg0, Object[] arg1, String arg2,
			Locale arg3) {
		 
		return this.delegator.getMessage(arg0, arg1, arg2, arg3);
	}

	@Override
	public void publishEvent(ApplicationEvent arg0) {
		this.delegator.publishEvent(arg0);
		
	}

	@Override
	public Resource[] getResources(String arg0) throws IOException {
		 
		return this.delegator.getResources(arg0);
	}

	@Override
	public ClassLoader getClassLoader() {
		
		return this.delegator.getClassLoader();
	}

	@Override
	public Resource getResource(String arg0) {
		 
		return this.delegator.getResource(arg0);
	}

	@Override
	public AutowireCapableBeanFactory getAutowireCapableBeanFactory()
			throws IllegalStateException {
		 
		return this.delegator.getAutowireCapableBeanFactory();
	}

	@Override
	public String getDisplayName() {
		 
		return this.delegator.getDisplayName();
	}

	@Override
	public String getId() {
		 
		return this.delegator.getId();
	}

	@Override
	public ApplicationContext getParent() {
		 
		return this.delegator.getParent();
	}

	@Override
	public long getStartupDate() {
		 
		return this.delegator.getStartupDate();
	}

}
