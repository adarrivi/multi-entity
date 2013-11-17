package com.multi.framework.service;

import com.multi.framework.domain.entity.EntityAttribute;
import com.multi.framework.domain.entity.Evolve;
import com.multi.framework.domain.entity.Listen;
import com.multi.framework.domain.entity.Move;
import com.multi.framework.domain.entity.See;
import com.multi.framework.domain.entity.Speak;
import com.multi.framework.domain.entity.Think;
import com.multi.framework.exception.ConfigurationException;
import com.multi.framework.service.impl.DefaultEvolveService;
import com.multi.framework.service.impl.DefaultListenService;
import com.multi.framework.service.impl.DefaultMoveService;
import com.multi.framework.service.impl.DefaultSeeService;
import com.multi.framework.service.impl.DefaultSpeakService;
import com.multi.framework.service.impl.DefaultThinkSerivce;

public enum EntityAttributeServiceType {

	LISTEN(Listen.class, ListenService.class, DefaultListenService.class), SEE(
			See.class, SeeService.class, DefaultSeeService.class), THINK(
			Think.class, ThinkService.class, DefaultThinkSerivce.class), EVOLVE(
			Evolve.class, EvolveService.class, DefaultEvolveService.class), MOVE(
			Move.class, MoveService.class, DefaultMoveService.class), SPEAK(
			Speak.class, SpeakService.class, DefaultSpeakService.class);

	private Class<? extends EntityAttribute> attributeInterfaceClass;
	private Class<? extends AttributeService> serviceInterfaceClass;
	private Class<? extends AttributeService> serviceDefaultClass;

	private EntityAttributeServiceType(
			Class<? extends EntityAttribute> entityInterfaceClass,
			Class<? extends AttributeService> serviceInterfaceClass,
			Class<? extends AttributeService> serviceDefaultClass) {
		this.attributeInterfaceClass = entityInterfaceClass;
		this.serviceInterfaceClass = serviceInterfaceClass;
		this.serviceDefaultClass = serviceDefaultClass;
	}

	public static boolean isDefaultImplementation(
			Class<? extends AttributeService> interfaceClass,
			Class<? extends AttributeService> aClass) {
		for (EntityAttributeServiceType type : EntityAttributeServiceType
				.values()) {
			if (interfaceClass.equals(type.serviceInterfaceClass)
					&& type.serviceDefaultClass.isAssignableFrom(aClass)) {
				return true;
			}
		}
		return false;
	}

	public static EntityAttributeServiceType getTypeByServiceInterface(
			Class<? extends AttributeService> interfaceClass) {
		for (EntityAttributeServiceType type : EntityAttributeServiceType
				.values()) {
			if (type.serviceInterfaceClass.equals(interfaceClass)) {
				return type;
			}
		}
		throw new ConfigurationException("Unexpected interface: "
				+ interfaceClass.getName());
	}

	public Class<? extends EntityAttribute> getAttributeInterfaceClass() {
		return attributeInterfaceClass;
	}

	public Class<? extends AttributeService> getServiceInterfaceClass() {
		return serviceInterfaceClass;
	}

}
