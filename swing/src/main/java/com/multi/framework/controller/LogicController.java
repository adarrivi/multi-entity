package com.multi.framework.controller;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.multi.framework.domain.entity.InternalEntity;
import com.multi.framework.service.ContextService;
import com.multi.framework.service.EvolveService;
import com.multi.framework.service.ListenService;
import com.multi.framework.service.MoveService;
import com.multi.framework.service.SeeService;
import com.multi.framework.service.SpeakService;
import com.multi.framework.service.ThinkService;
import com.multi.framework.util.BeanLocator;

@Controller
public class LogicController {

	@Autowired
	private BeanLocator beanLocator;

	@Autowired
	private EntitiesContiner entitiesContiner;

	private ContextService contextService;
	private ListenService listenService;
	private SeeService seeService;
	private ThinkService thinkService;
	private EvolveService evolveService;
	private MoveService moveService;
	private SpeakService speakService;

	@PostConstruct
	private void init() {
		contextService = beanLocator
				.getServiceImplFromInterface(ContextService.class);
		listenService = beanLocator
				.getServiceImplFromInterface(ListenService.class);
		seeService = beanLocator.getServiceImplFromInterface(SeeService.class);
		thinkService = beanLocator
				.getServiceImplFromInterface(ThinkService.class);
		evolveService = beanLocator
				.getServiceImplFromInterface(EvolveService.class);
		moveService = beanLocator
				.getServiceImplFromInterface(MoveService.class);
		speakService = beanLocator
				.getServiceImplFromInterface(SpeakService.class);
	}

	public void step() {
		Collection<InternalEntity> internalEntities = entitiesContiner
				.getInternalEntitiesByAttributeService(listenService.getClass());

	}

}
