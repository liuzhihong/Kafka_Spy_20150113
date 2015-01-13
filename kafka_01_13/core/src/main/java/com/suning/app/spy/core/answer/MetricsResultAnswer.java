package com.suning.app.spy.core.answer;

import com.suning.shared.spy.model.MetricsTarget;
import com.suning.shared.spy.model.TmpMetricsModel;

/**
 * @author karry 2015-1-7 下午3:49:25
 */
public interface MetricsResultAnswer {
	
	public void onInit(MetricsTarget target);
	
	public void onSend(TmpMetricsModel tmpMetricsModel);

}
