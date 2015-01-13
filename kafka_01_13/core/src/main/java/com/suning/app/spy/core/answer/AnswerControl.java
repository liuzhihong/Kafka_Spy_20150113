package com.suning.app.spy.core.answer;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import com.suning.app.spy.core.AbstractLifeCycle;
import com.suning.app.spy.core.Configuration;
import com.suning.shared.spy.model.MetricsTarget;
import com.suning.shared.spy.model.TmpMetricsModel;

/**
 * 类AnswerControl.java的实现描述
 * @author karry 2014-10-28 下午6:05:26
 */
public class AnswerControl extends AbstractLifeCycle {


	private static AnswerControl inst = new AnswerControl();
	
	private List<MetricsResultAnswer> answerList = new ArrayList<MetricsResultAnswer>();


	public static AnswerControl getInstance() {

		return inst;
	}

	private AnswerControl() {

	}
	
	
	public void send(TmpMetricsModel tmpMetricsModel) {
		if (null != answerList) {
			for (MetricsResultAnswer answer:answerList) {
				answer.onSend(tmpMetricsModel);
			}
		}
	}
	
	public void init(MetricsTarget target) {
		if (null != answerList) {
			for (MetricsResultAnswer answer:answerList) {
				answer.onInit(target);
			}
		}
	}
	
	public void registeAnswer(MetricsResultAnswer answer) {
		answerList.add(answer);
	}
	
	public void unRegisteAnswer(MetricsResultAnswer answer) {
		answerList.remove(answer);
	}

	@Override
	protected void doStart(Configuration conf) throws Exception {

		for (MetricsResultAnswer answer : ServiceLoader.load(MetricsResultAnswer.class, AnswerControl.class.getClassLoader())) {
			registeAnswer(answer);		
		}
		super.doStart(conf);
	}

}
