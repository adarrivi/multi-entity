package com.multi.framework.domain.context;

import java.util.Collection;

public interface Context<T extends ContextRule<K>, K extends ContextViolation> {

	Collection<T> getRules();
}
