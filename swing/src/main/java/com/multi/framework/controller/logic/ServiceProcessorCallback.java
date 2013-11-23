package com.multi.framework.controller.logic;

import com.multi.framework.container.InternalEntitiesServiceWrapper;

public interface ServiceProcessorCallback {
	void processService(InternalEntitiesServiceWrapper serviceWrapper);
}
