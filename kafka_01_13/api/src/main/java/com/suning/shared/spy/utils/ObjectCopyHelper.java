/*
 * Copyright (C), 2002-2014, 苏宁易购电子商务有限公司
 * FileName: ObjectCopyHelper.java
 * Author:   13073050
 * Date:     2014年12月12日 下午2:39:23
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.shared.spy.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.suning.shared.spy.model.MetricsInfoModel;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author 13073050
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ObjectCopyHelper {
    
    private static final Logger LOGGER = Logger.getLogger(ObjectCopyHelper.class);
    /**
     * 
     * 功能描述: 对象的深拷贝
     *
     * @param model
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @SuppressWarnings("unchecked")
    public static <T extends MetricsInfoModel> T cpoyObject(T model){
        T cloneObj = null; 
        ObjectOutputStream obs = null;
        ObjectInputStream ois = null;
        try {  
            //写入字节流  
            ByteArrayOutputStream out = new ByteArrayOutputStream();  
            obs = new ObjectOutputStream(out);  
            obs.writeObject(model);  
              
            //分配内存，写入原始对象，生成新对象  
            ByteArrayInputStream ios = new ByteArrayInputStream(out.toByteArray());  
            ois = new ObjectInputStream(ios);  
            //返回生成的新对象  
            cloneObj = (T) ois.readObject();  
        } catch (Exception e) {  
            LOGGER.error(e.getMessage(), e);  
        } finally {
            try {
                if(ois != null) ois.close();
                if(obs != null) ois.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return cloneObj;  
    }
}
