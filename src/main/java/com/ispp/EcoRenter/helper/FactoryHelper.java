/*
 * FactoryHelper.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package com.ispp.EcoRenter.helper;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


public class FactoryHelper {

	// Internal methods -------------------------------------------------------

	private static ApplicationContext			context;

	private static MessageSource				messageSource;

	private static ConversionService			conversionService;

	private static Validator					validator;

	private static PasswordEncoder				passwordEncoder;

	private static EntityManager				entityManager;

	private static AutowireCapableBeanFactory	autowiringFactory;


	// Constructors -----------------------------------------------------------

	public static void initialise(final ApplicationContext context) {
		assert context != null;
		assert !FactoryHelper.isInitialised();

		FactoryHelper.context = context;
		FactoryHelper.autowiringFactory = context.getAutowireCapableBeanFactory();
		FactoryHelper.messageSource = context.getBean(MessageSource.class);
		FactoryHelper.conversionService = (ConversionService) context.getBean("conversionService");
		FactoryHelper.validator = context.getBean(LocalValidatorFactoryBean.class);
		FactoryHelper.passwordEncoder = context.getBean(PasswordEncoder.class);
		FactoryHelper.entityManager = context.getBean(EntityManager.class);
	}

	
	
	// Business methods -------------------------------------------------------

	public static ApplicationContext getContext() {
		return context;
	}



	public static MessageSource getMessageSource() {
		return messageSource;
	}



	public static ConversionService getConversionService() {
		return conversionService;
	}



	public static Validator getValidator() {
		return validator;
	}



	public static PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}



	public static EntityManager getEntityManager() {
		return entityManager;
	}


	public static AutowireCapableBeanFactory getAutowiringFactory() {
		return autowiringFactory;
	}

	private static boolean isInitialised() {
		boolean result;

		result = FactoryHelper.context != null;

		return result;
	}

	public static void autowire(final Iterable<?> objects) {
		assert !CollectionHelper.someNull(objects);
		assert FactoryHelper.isInitialised();

		for (final Object object : objects) {
			FactoryHelper.autowire(object);
		}
	}

	public static <T> void autowire(final T object) {
		assert object != null;
		assert FactoryHelper.isInitialised();

		FactoryHelper.autowiringFactory.autowireBean(object);
	}

	public static <T> T getBean(final Class<T> clazz) {
		assert clazz != null;
		assert FactoryHelper.isInitialised();

		T result;

		result = FactoryHelper.context.getBean(clazz);

		return result;
	}

	public static Object getBean(final String name) {
		assert !StringHelper.isBlank(name);
		assert FactoryHelper.isInitialised();

		Object result;

		result = FactoryHelper.context.getBean(name);

		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(final String name, final Class<T> clazz) {
		assert !StringHelper.isBlank(name);
		assert clazz != null;
		assert FactoryHelper.isInitialised();

		T result;
		Object bean;

		bean = FactoryHelper.context.getBean(name);
		assert clazz.isAssignableFrom(bean.getClass());
		result = (T) bean;

		return result;
	}

}
