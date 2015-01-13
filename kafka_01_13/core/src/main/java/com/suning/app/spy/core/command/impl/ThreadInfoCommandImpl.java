/*
 * Copyright (C), 2002-2014, 苏宁易购电子商务有限公司
 * FileName: ThreadInfoCommandImpl.java
 * Author:   13073050
 * Date:     2014年12月30日 下午2:45:56
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
import com.suning.shared.spy.model.biz.AggreateThreadInfoModel;
import com.suning.shared.spy.model.biz.AppMetricsKeyModel;
import com.suning.shared.spy.model.biz.AggreateThreadInfoModel.ThreadInfoModel;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author 13073050
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ThreadInfoCommandImpl extends AbstractCommandHandler<AggreateThreadInfoModel> {

    private static final Logger LOGGER = Logger.getLogger(ThreadInfoCommandImpl.class);
    private JmxOperateService jmxOperateService = 
            ServiceControl.getInstance().getBean(JmxOperateService.class);
    
    @Override
    public String getCommandName() {
        return Constants.METRICS_THREAD;
    }

    @Override
    public AggreateThreadInfoModel doHandle(String[] cmdList) {
        LOGGER.info("Excute doHandle method , Get Thread Information !");
        AggreateThreadInfoModel aggreateThreadInfoModel = new AggreateThreadInfoModel();
        Map<AppMetricsKeyModel,ThreadInfoModel> maps = this.jmxOperateService.getAllThreadInfo();
        Map<String,ThreadInfoModel> extThreadInfo = new HashMap<String, AggreateThreadInfoModel.ThreadInfoModel>();
        for(Map.Entry<AppMetricsKeyModel, ThreadInfoModel> entry : maps.entrySet()){
            AppMetricsKeyModel keyModel = entry.getKey();
            if ((keyModel.getIp().equals(MachineInfoUtil.getMachineIp()) || keyModel.getIp().equals(Constants.LOCAL_IP))
                    && !keyModel.isTarget()){
                //保存自身监控的线程信息
                aggreateThreadInfoModel.setSelfThreadInfo(entry.getValue());
            } else if (keyModel.isTarget()){
                //保存目标监控对象的线程信息
                aggreateThreadInfoModel.setTargetThreadInfo(entry.getValue());
            } else {
                //保存扩展监控对象的线程信息
                extThreadInfo.put(keyModel.toString(),entry.getValue());
            }
        }
        aggreateThreadInfoModel.setExtThreadInfo(extThreadInfo);
        return aggreateThreadInfoModel;
    }

}
