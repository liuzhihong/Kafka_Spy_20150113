/*
 * Copyright (C), 2002-2014, 苏宁易购电子商务有限公司
 * FileName: AggreateClassesInfoModel.java
 * Author:   13073050
 * Date:     2014年12月30日 下午3:35:37
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
 * 监控自身，目标对象以及扩展对象的class信息
 *
 * @author 13073050
 * @see 
 * @since 
 */
public class AggreateClassesInfoModel extends MetricsInfoModel {

    /**
     */
    private static final long serialVersionUID = -1360668115144283218L;

    private Map<String, ClassesInfoModel> extClassesInfo;
    
    private ClassesInfoModel selfClassInfo;
    
    private ClassesInfoModel targetClassInfo;
    
    public Map<String, ClassesInfoModel> getExtClassesInfo() {
        return extClassesInfo;
    }

    public void setExtClassesInfo(Map<String, ClassesInfoModel> extClassesInfo) {
        this.extClassesInfo = extClassesInfo;
    }

    public ClassesInfoModel getSelfClassInfo() {
        return selfClassInfo;
    }

    public void setSelfClassInfo(ClassesInfoModel selfClassInfo) {
        this.selfClassInfo = selfClassInfo;
    }

    public ClassesInfoModel getTargetClassInfo() {
        return targetClassInfo;
    }

    public void setTargetClassInfo(ClassesInfoModel targetClassInfo) {
        this.targetClassInfo = targetClassInfo;
    }

    /* (non-Javadoc)
     * @see com.suning.shared.spy.model.MetricsInfoModel#getType()
     */
    @Override
    public MetricsType getType() {
        return MetricsType.CLASSES;
    }
    
    public static class ClassesInfoModel implements Serializable {

        /**
         */
        private static final long serialVersionUID = 1884814893240968350L;
        
        private int loadedClassCount;//已加载的class数量
        private long totalClassCount;//总的class数量
        
        public int getLoadedClassCount() {
            return loadedClassCount;
        }
        public void setLoadedClassCount(int loadedClassCount) {
            this.loadedClassCount = loadedClassCount;
        }
        public long getTotalClassCount() {
            return totalClassCount;
        }
        public void setTotalClassCount(long totalClassCount) {
            this.totalClassCount = totalClassCount;
        }
        
        
    }
}
