package com.multi.framework.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.multi.framework.exception.ConfigurationException;
import com.multi.framework.service.AttributeService;
import com.multi.framework.service.EntityAttributeServiceType;

@Component
public class BeanLocator {

	private static final Logger LOG = LoggerFactory
			.getLogger(BeanLocator.class);

	@Autowired
	private ApplicationContext applicationContext;

	public <T extends AttributeService> T getServiceImplFromInterface(
			Class<T> interfaceBeanClass) {
		Map<String, T> serviceBeans = applicationContext
				.getBeansOfType(interfaceBeanClass);
		validateServiceConfiguration(serviceBeans, interfaceBeanClass);
		return getOverridenOrDefaultBean(interfaceBeanClass,
				serviceBeans.entrySet());
	}

	private <T extends AttributeService> void validateServiceConfiguration(
			Map<String, T> servicetBeans,
			Class<? extends T> interfaceServiceClass) {
		if (servicetBeans.isEmpty()) {
			throw new ConfigurationException(
					"No bean implementation found for "
							+ interfaceServiceClass.getName());
		}
		if (servicetBeans.size() > 2) {
			throw new ConfigurationException(
					"Expected no more than 2 implementations (default provided by framework and another one overriding it) for the interface "
							+ interfaceServiceClass.getName()
							+ ", but found "
							+ servicetBeans.size());
		}
	}

	private <T extends AttributeService> T getOverridenOrDefaultBean(
			Class<T> interfaceServiceClass,
			Set<Entry<String, T>> serviceEntrySet) {
		if (serviceEntrySet.size() == 1) {
			LOG.debug("No overriden implementation found for {}",
					interfaceServiceClass.getName());
			return serviceEntrySet.iterator().next().getValue();
		}
		for (Entry<String, T> entry : serviceEntrySet) {
			T bean = entry.getValue();
			if (!EntityAttributeServiceType.isDefaultImplementation(
					interfaceServiceClass, bean.getClass())) {
				LOG.debug("Found custom implementation for {}: {}",
						interfaceServiceClass.getName(), bean.getClass()
								.getName());
				return bean;
			}
		}
		throw new ConfigurationException(
				"Impossible to find a default or overriden implementation for the interface "
						+ interfaceServiceClass.getName());
	}

}
