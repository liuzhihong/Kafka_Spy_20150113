package com.suning.app.spy.core.support;
public interface JmxConnLifeCycleProcessor {
	
	void beforConnect(JmxClient client);
	void afterConnect(JmxClient client);
	void afterDisConnect(JmxClient client);
	void beforeReConnect(JmxClient client);

}
