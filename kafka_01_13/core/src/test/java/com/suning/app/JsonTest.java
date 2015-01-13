package com.suning.app;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.suning.shared.spy.model.MetricsTarget;
import com.suning.shared.spy.model.TmpMetricsModel;
import com.suning.shared.spy.model.UnitedMetricsModel;
import com.suning.shared.spy.model.biz.AggregateGCInfoModel;
import com.suning.shared.spy.model.biz.GCInfoModel;

public class JsonTest {
	    
    public static TmpMetricsModel fetchParams() {
    	MetricsTarget target = new MetricsTarget("1","2","3");
    	TmpMetricsModel innerModel = new TmpMetricsModel(target);		
		GCInfoModel gcInfo = new GCInfoModel();
		gcInfo.setFullGCCount(0.2);
		AggregateGCInfoModel gcInfoList = new AggregateGCInfoModel();
		gcInfoList.setSelfGcInfo(gcInfo);
		Map<String, GCInfoModel> extGcInfo = new HashMap<String, GCInfoModel>();
		extGcInfo.put("testExt", gcInfo);
		
		innerModel.setMetricsModel(gcInfoList);
		return innerModel;
    }
    

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		TmpMetricsModel inModel = fetchParams();	
		String json = JSON.toJSONString(inModel, SerializerFeature.WriteClassName);
		UnitedMetricsModel outModel = JSON.parseObject(json, UnitedMetricsModel.class);
		System.out.println(outModel);
	}

}
