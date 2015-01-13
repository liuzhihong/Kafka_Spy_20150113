package com.suning.app.spy.core.answer.impl;

import org.apache.log4j.Logger;

import com.suning.app.spy.core.answer.MetricsResultAnswer;
import com.suning.app.spy.core.service.ServiceControl;
import com.suning.app.spy.core.service.impl.ZookeeperOperateService;
import com.suning.shared.spy.model.MetricsTarget;
import com.suning.shared.spy.model.TmpMetricsModel;

public class ZkAnswerImpl implements MetricsResultAnswer {
	
	private ZookeeperOperateService zkService;
	
	private static final Logger LOGGER = Logger.getLogger(ZkAnswerImpl.class);

	
	public ZkAnswerImpl() {
		this.zkService = ServiceControl.getInstance().getBean(ZookeeperOperateService.class);
	}

	@Override
	public void onSend(TmpMetricsModel tmpMetricsModel) {
		
		zkService.Send(tmpMetricsModel);
	}

	@Override
	public void onInit(MetricsTarget target) {
		if (!zkService.postMetricsTarget(target)) {
			LOGGER.error("zk init error");
			System.exit(-1);
		}
		
	}

}
