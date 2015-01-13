/*
 * Copyright (C), 2002-2014, 苏宁易购电子商务有限公司
 * FileName: JvmInfoCommandImpl.java
 * Author:   13073050
 * Date:     2014年10月31日 下午4:49:08
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.app.spy.core.command.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.suning.app.spy.core.command.AbstractCommandHandler;
import com.suning.app.spy.core.constants.Constants;
import com.suning.app.spy.core.service.ServiceControl;
import com.suning.app.spy.core.service.impl.JmxOperateService;
import com.suning.app.spy.core.utils.MachineInfoUtil;
import com.suning.shared.spy.model.biz.AggregateHeapInfoModel;
import com.suning.shared.spy.model.biz.AppMetricsKeyModel;
import com.suning.shared.spy.model.biz.HeapInfoModel;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author 13073050
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class HeapInfoCommandImpl extends AbstractCommandHandler<AggregateHeapInfoModel>{
    private static final Logger LOGGER = Logger.getLogger(HeapInfoCommandImpl.class); 
    
    private JmxOperateService jmxOperateService = 
            ServiceControl.getInstance().getBean(JmxOperateService.class);

	@Override
	public String getCommandName() {
		return Constants.METRICS_HEAP;
	}

	@Override
	public AggregateHeapInfoModel doHandle(String[] cmdList) {
		LOGGER.info("Excute doHandle method , Get Jvm Information !");
		AggregateHeapInfoModel aggregateHeapInfoModel = new AggregateHeapInfoModel();
		Map<AppMetricsKeyModel,HeapInfoModel> maps = this.jmxOperateService.getAllHeapInfo();
		Map<String,HeapInfoModel> extHeapInfo = new HashMap<String, HeapInfoModel>();
        for(Map.Entry<AppMetricsKeyModel, HeapInfoModel> entry : maps.entrySet()){
            AppMetricsKeyModel keyModel = entry.getKey();
            if ((keyModel.getIp().equals(MachineInfoUtil.getMachineIp()) || keyModel.getIp().equals(Constants.LOCAL_IP))
                    && !keyModel.isTarget()){
                //保存自身监控的Heap信息
                aggregateHeapInfoModel.setSelfHeapInfo(entry.getValue());
            } else if (keyModel.isTarget()){
                //保存目标监控对象的Heap信息
                aggregateHeapInfoModel.setTargetHeapInfo(entry.getValue());
            } else {
                //保存扩展监控对象的Heap信息
                extHeapInfo.put(keyModel.toString(),entry.getValue());
            }
        }
        aggregateHeapInfoModel.setExtHeapInfo(extHeapInfo);
        return aggregateHeapInfoModel;
	}
}
