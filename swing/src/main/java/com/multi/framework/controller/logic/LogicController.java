package com.multi.framework.controller.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.multi.framework.container.AttributeType;
import com.multi.framework.container.EntitiesContiner;
import com.multi.framework.container.InternalEntitiesServiceWrapper;

@Controller
public class LogicController {

    @Autowired
    private EntitiesContiner entitiesContiner;

    // TODO Move this list into AttributeType
    @Autowired
    @Qualifier("listenProcessor")
    private ServiceProcessorCallback listenProcessor;
    @Qualifier("seeProcessor")
    private ServiceProcessorCallback seeProcessor;
    @Autowired
    @Qualifier("thinkProcessor")
    private ServiceProcessorCallback thinkProcessor;
    @Autowired
    @Qualifier("evolveProcessor")
    private ServiceProcessorCallback evolveProcessor;
    @Autowired
    @Qualifier("moveProcessor")
    private ServiceProcessorCallback moveProcessor;
    @Autowired
    @Qualifier("speakProcessor")
    private ServiceProcessorCallback speakProcessor;
    @Autowired
    @Qualifier("surviveProcessor")
    private ServiceProcessorCallback surviveProcessor;

    public void step() {
        processServicesByType(AttributeType.LISTEN, listenProcessor);
        processServicesByType(AttributeType.SEE, seeProcessor);
        processServicesByType(AttributeType.THINK, thinkProcessor);
        processServicesByType(AttributeType.EVOLVE, evolveProcessor);
        processServicesByType(AttributeType.MOVE, moveProcessor);
        processServicesByType(AttributeType.SPEAK, speakProcessor);
        processServicesByType(AttributeType.SURVIVE, surviveProcessor);
    }

    private void processServicesByType(AttributeType type, ServiceProcessorCallback callbackProcessor) {
        List<InternalEntitiesServiceWrapper> serviceWrapperList = entitiesContiner.getInternalEntitiesByAttributeService(type);
        for (InternalEntitiesServiceWrapper serviceWrapper : serviceWrapperList) {
            callbackProcessor.processService(serviceWrapper);
        }
    }

}
