package com.multi.framework.domain.context;

import java.util.Collection;

public interface ContextRule<T extends ContextViolation> {

	Collection<T> getViolations();

}
