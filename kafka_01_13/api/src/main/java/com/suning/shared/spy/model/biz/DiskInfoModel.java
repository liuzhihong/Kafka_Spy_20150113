/*
 * Copyright (C), 2002-2014, 苏宁易购电子商务有限公司
 * FileName: DiskInfo.java
 * Author:   13073050
 * Date:     2014年10月28日 下午9:13:10
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.shared.spy.model.biz;

import java.util.Map;

import com.suning.shared.spy.enums.MetricsType;
import com.suning.shared.spy.model.MetricsInfoModel;

/**
 * 〈一句话功能简述〉<br> 
 *  linux命令：iostat -d -k -x && df -hm
 * @author 13073050
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DiskInfoModel extends MetricsInfoModel{
    /**
     */
    private static final long serialVersionUID = 8540986833982850310L;
    public Map<String, DiskCatalogInfoModel> catalogs;
    public Map<String,DiskIOInfoModel> ioInfo;
    
    public Map<String, DiskCatalogInfoModel> getCatalogs() {
        return catalogs;
    }
    
    public Map<String, DiskIOInfoModel> getIoInfo() {
        return ioInfo;
    }

    @Override
    public MetricsType getType() {
        return MetricsType.DISK;
    }
    
    public void setCatalogs(Map<String, DiskCatalogInfoModel> catalogs) {
        this.catalogs = catalogs;
    }

    public void setIoInfo(Map<String, DiskIOInfoModel> ioInfo) {
        this.ioInfo = ioInfo;
    }
    
    
}