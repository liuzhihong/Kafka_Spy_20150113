/*
 * Copyright (C), 2002-2014, 苏宁易购电子商务有限公司
 * FileName: ClassesInfoCommandImpl.java
 * Author:   13073050
 * Date:     2014年12月30日 下午4:33:27
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
import com.suning.shared.spy.model.biz.AggreateClassesInfoModel;
import com.suning.shared.spy.model.biz.AppMetricsKeyModel;
import com.suning.shared.spy.model.biz.AggreateClassesInfoModel.ClassesInfoModel;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author 13073050
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ClassesInfoCommandImpl extends AbstractCommandHandler<AggreateClassesInfoModel> {

    private static final Logger LOGGER = Logger.getLogger(ClassesInfoCommandImpl.class);
    private JmxOperateService jmxOperateService = 
            ServiceControl.getInstance().getBean(JmxOperateService.class);
    @Override
    public String getCommandName() {
        return Constants.METRICS_CLASSES;
    }

    @Override
    public AggreateClassesInfoModel doHandle(String[] cmdList) {
        LOGGER.info("Excute doHandle method , Get Classes Information !");
        AggreateClassesInfoModel aggreateClassesInfoModel = new AggreateClassesInfoModel();
        Map<AppMetricsKeyModel,ClassesInfoModel> maps = this.jmxOperateService.getAllClassInfo();
        Map<String,ClassesInfoModel> extClassInfo = new HashMap<String, AggreateClassesInfoModel.ClassesInfoModel>();
        for(Map.Entry<AppMetricsKeyModel, ClassesInfoModel> entry : maps.entrySet()){
            AppMetricsKeyModel keyModel = entry.getKey();
            if ((keyModel.getIp().equals(MachineInfoUtil.getMachineIp()) || keyModel.getIp().equals(Constants.LOCAL_IP))
                    && !keyModel.isTarget()){
                //保存自身监控的class信息
                aggreateClassesInfoModel.setSelfClassInfo(entry.getValue());
            } else if (keyModel.isTarget()){
                //保存目标监控对象的线程信息
                aggreateClassesInfoModel.setTargetClassInfo(entry.getValue());
            } else {
                //保存扩展监控对象的线程信息
                extClassInfo.put(keyModel.toString(),entry.getValue());
            }
        }
        aggreateClassesInfoModel.setExtClassesInfo(extClassInfo);
        return aggreateClassesInfoModel;
    }

}
