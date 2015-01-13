/*
 * Copyright (C), 2002-2014, 苏宁易购电子商务有限公司
 * FileName: DiskIOInfoModel.java
 * Author:   13073050
 * Date:     2014年12月8日 下午4:31:14
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.shared.spy.model.biz;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author 13073050
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DiskIOInfoModel implements Serializable {

    /**
     */
    private static final long serialVersionUID = 6506733557285368303L;

    public float readSpeed; // KB/s
    public float writeSpeed; // KB/s
    public float awaitTime; // IO响应时间，单位ms
    public float percentUtil; // IO利用率

    public float getReadSpeed() {
        return readSpeed;
    }

    public float getWriteSpeed() {
        return writeSpeed;
    }

    public float getAwaitTime() {
        return awaitTime;
    }

    public float getPercentUtil() {
        return percentUtil;
    }

    // /////////////////write////////////////////
    public void setReadSpeed(float readSpeed) {
        this.readSpeed = readSpeed;
    }

    public void setWriteSpeed(float writeSpeed) {
        this.writeSpeed = writeSpeed;
    }

    public void setAwaitTime(float awaitTime) {
        this.awaitTime = awaitTime;
    }

    public void setPercentUtil(float percentUtil) {
        this.percentUtil = percentUtil;
    }
}
