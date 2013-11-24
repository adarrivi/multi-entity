package com.multi.framework.container;

import com.multi.framework.domain.entity.EntityAttribute;
import com.multi.framework.domain.entity.action.Evolve;
import com.multi.framework.domain.entity.action.Listen;
import com.multi.framework.domain.entity.action.Move;
import com.multi.framework.domain.entity.action.See;
import com.multi.framework.domain.entity.action.Speak;
import com.multi.framework.domain.entity.action.Survive;
import com.multi.framework.domain.entity.action.Think;
import com.multi.framework.exception.ConfigurationException;
import com.multi.framework.service.AttributeService;
import com.multi.framework.service.EvolveService;
import com.multi.framework.service.ListenService;
import com.multi.framework.service.MoveService;
import com.multi.framework.service.SeeService;
import com.multi.framework.service.SpeakService;
import com.multi.framework.service.SurviveService;
import com.multi.framework.service.ThinkService;
import com.multi.framework.service.impl.DefaultBlindService;
import com.multi.framework.service.impl.DefaultDeafService;
import com.multi.framework.service.impl.DefaultDumbSerivce;
import com.multi.framework.service.impl.DefaultImmortalService;
import com.multi.framework.service.impl.DefaultImmutableService;
import com.multi.framework.service.impl.DefaultSilentService;
import com.multi.framework.service.impl.DefaultStillService;

//TODO Fix the generics mess!
@SuppressWarnings("rawtypes")
public enum AttributeType {

    LISTEN(Listen.class, ListenService.class, DefaultDeafService.class),
    SEE(See.class, SeeService.class, DefaultBlindService.class),
    THINK(Think.class, ThinkService.class, DefaultDumbSerivce.class),
    EVOLVE(Evolve.class, EvolveService.class, DefaultImmutableService.class),
    MOVE(Move.class, MoveService.class, DefaultStillService.class),
    SPEAK(Speak.class, SpeakService.class, DefaultSilentService.class),
    SURVIVE(Survive.class, SurviveService.class, DefaultImmortalService.class);

    private Class<? extends EntityAttribute> attributeInterfaceClass;
    private Class<? extends AttributeService> serviceInterfaceClass;
    private Class<? extends AttributeService> serviceDefaultClass;

    private AttributeType(Class<? extends EntityAttribute> entityInterfaceClass, Class<? extends AttributeService> serviceInterfaceClass,
            Class<? extends AttributeService> serviceDefaultClass) {
        this.attributeInterfaceClass = entityInterfaceClass;
        this.serviceInterfaceClass = serviceInterfaceClass;
        this.serviceDefaultClass = serviceDefaultClass;
    }

    public static boolean isDefaultImplementation(Class<? extends AttributeService> interfaceClass, Class<? extends AttributeService> aClass) {
        for (AttributeType type : AttributeType.values()) {
            if (interfaceClass.equals(type.serviceInterfaceClass) && type.serviceDefaultClass.isAssignableFrom(aClass)) {
                return true;
            }
        }
        return false;
    }

    public static AttributeType getTypeByServiceInterface(Class<? extends AttributeService> interfaceClass) {
        for (AttributeType type : AttributeType.values()) {
            if (type.serviceInterfaceClass.equals(interfaceClass)) {
                return type;
            }
        }
        throw new ConfigurationException("Unexpected interface: " + interfaceClass.getName());
    }

    public Class<? extends EntityAttribute> getAttributeInterfaceClass() {
        return attributeInterfaceClass;
    }

    public Class<? extends AttributeService> getServiceInterfaceClass() {
        return serviceInterfaceClass;
    }

}
