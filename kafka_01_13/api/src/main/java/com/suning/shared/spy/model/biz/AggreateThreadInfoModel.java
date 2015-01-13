/*
 * Copyright (C), 2002-2014, 苏宁易购电子商务有限公司
 * FileName: AggreateThreadInfoModel.java
 * Author:   13073050
 * Date:     2014年12月30日 上午11:32:27
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.shared.spy.model.biz;

import java.io.Serializable;
import java.util.Map;

import com.suning.shared.spy.enums.MetricsType;
import com.suning.shared.spy.model.MetricsInfoModel;

/**
 * 〈extThreadInfo> 扩展的远程监控对象线程信息
 *  <targetThreadInfo> 目标监控对象线程信息
 *  <selfThreadInfo> 自身线程信息
 *
 * @author 13073050
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class AggreateThreadInfoModel extends MetricsInfoModel {

    /**
     */
    private static final long serialVersionUID = 2921278799792654641L;

    private Map<String,ThreadInfoModel> extThreadInfo;
    
    private ThreadInfoModel targetThreadInfo;
    
    private ThreadInfoModel selfThreadInfo;
    
    
    public Map<String, ThreadInfoModel> getExtThreadInfo() {
        return extThreadInfo;
    }
    public void setExtThreadInfo(Map<String, ThreadInfoModel> extThreadInfo) {
        this.extThreadInfo = extThreadInfo;
    }
    public ThreadInfoModel getTargetThreadInfo() {
        return targetThreadInfo;
    }
    public void setTargetThreadInfo(ThreadInfoModel targetThreadInfo) {
        this.targetThreadInfo = targetThreadInfo;
    }
    public ThreadInfoModel getSelfThreadInfo() {
        return selfThreadInfo;
    }
    public void setSelfThreadInfo(ThreadInfoModel selfThreadInfo) {
        this.selfThreadInfo = selfThreadInfo;
    }
    /* (non-Javadoc)
     * @see com.suning.shared.spy.model.MetricsInfoModel#getType()
     */
    @Override
    public MetricsType getType() {
        return MetricsType.THREAD;
    }
    public static class ThreadInfoModel implements Serializable{

        /**
         */
        private static final long serialVersionUID = -8988289857024448312L;
        private long [] deadLockThreadsId;//死锁线程ID
        private int activeThreadCount;//活动线程的数量
        
        public long[] getDeadLockThreadsId() {
            return deadLockThreadsId;
        }
        public void setDeadLockThreadsId(long[] deadLockThreadsId) {
            this.deadLockThreadsId = deadLockThreadsId;
        }
        public int getActiveThreadCount() {
            return activeThreadCount;
        }
        public void setActiveThreadCount(int activeThreadCount) {
            this.activeThreadCount = activeThreadCount;
        }
        
    }
}
