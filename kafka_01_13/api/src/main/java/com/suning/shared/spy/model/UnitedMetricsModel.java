package com.suning.shared.spy.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.suning.shared.spy.enums.MetricsType;
import com.suning.shared.spy.model.biz.AggreateClassesInfoModel;
import com.suning.shared.spy.model.biz.AggreateThreadInfoModel;
import com.suning.shared.spy.model.biz.AggregateGCInfoModel;
import com.suning.shared.spy.model.biz.AggregateHeapInfoModel;
import com.suning.shared.spy.model.biz.CpuInfoModel;
import com.suning.shared.spy.model.biz.DiskInfoModel;
import com.suning.shared.spy.model.biz.NetInfoModel;
import com.suning.shared.spy.model.biz.VmMemInfoModel;

/**
 * 类UnitedMetricsModel.java的实现描述
 * 
 * @author karry 2014-10-29 下午2:35:47
 */
public class UnitedMetricsModel implements Serializable {

	private static final long serialVersionUID = -2283555842100914153L;

	private Map<String, MetricsInfoModel> metricsModels = new HashMap<String, MetricsInfoModel>(MetricsType.size());

	private MetricsTarget metricsTarget;

	public Map<String, MetricsInfoModel> getMetricsModels() {
		return metricsModels;
	}
	

	public CpuInfoModel getCpuInfoModel() {
		return (CpuInfoModel) metricsModels.get(MetricsType.CPU.getName());
	}

	public boolean hasCpuInfo() {
		return metricsModels.get(MetricsType.CPU.getName())!=null;
	}

	public VmMemInfoModel getMemInfoModel() {
		return (VmMemInfoModel) metricsModels.get(MetricsType.MEM.getName());
	}

	public boolean hasMemInfo() {
		return metricsModels.get(MetricsType.MEM.getName())!=null;
	}

	public DiskInfoModel getDiskInfoModel() {
		return (DiskInfoModel) metricsModels.get(MetricsType.DISK.getName());
	}

	public boolean hasDiskInfo() {
		return metricsModels.get(MetricsType.DISK.getName())!=null;
	}

	public NetInfoModel getNetInfoModel() {
		return (NetInfoModel) metricsModels.get(MetricsType.NET.getName());
	}

	public boolean hasNetInfo() {
		return metricsModels.get(MetricsType.NET.getName())!=null;
	}

	public AggregateGCInfoModel getGCInfoModel() {
		return (AggregateGCInfoModel) metricsModels.get(MetricsType.GC.getName());
	}

	public boolean hasGCInfo() {
		return metricsModels.get(MetricsType.GC.getName())!=null;
	}

	public AggregateHeapInfoModel getHeapInfoModel() {
		return (AggregateHeapInfoModel) metricsModels.get(MetricsType.HEAP.getName());
	}

	public boolean hasHeapInfo() {
		return metricsModels.get(MetricsType.HEAP.getName())!=null;
	}

	public boolean hasThreadInfo() {
	    return metricsModels.get(MetricsType.THREAD.getName()) != null;
	}
	
	public AggreateThreadInfoModel getThreadInfoModel(){
	    return (AggreateThreadInfoModel) metricsModels.get(MetricsType.THREAD.getName());
	}
	
	public boolean hasClassesInfoModel(){
	    return metricsModels.get(MetricsType.CLASSES.getName()) != null;
	}
	
	public AggreateClassesInfoModel getClassInfoModel(){
	    return (AggreateClassesInfoModel) metricsModels.get(MetricsType.CLASSES.getName());
	}
	// //////////////////write//////////////////////

	public MetricsTarget getMetricsTarget() {
		return metricsTarget;
	}


	public UnitedMetricsModel() {

	}

	public UnitedMetricsModel(Map<String, MetricsInfoModel> metricsModels, MetricsTarget metricsTarget) {
		this.metricsModels = metricsModels;
		this.metricsTarget = metricsTarget;
	}

	@Override
	public String toString() {
		return this.metricsTarget.getAppName() + "/" + this.metricsTarget.getClusterName() + "/" + this.metricsTarget.getIp();
	}
	
	public static void main(String[] args) throws Exception {
//		UnitedMetricsModel m = new UnitedMetricsModel(new MetricsTarget("dddd","eeee","fffff"));
//		
//		m.setMetricsModel(new CpuInfoModel());
		
//		String json = JSON.toJSONString(m, SerializerFeature.WriteClassName);
//		UnitedMetricsModel n = JSON.parseObject(json, UnitedMetricsModel.class);
//		System.out.println(n);
//		JsonConfig jc = new JsonConfig();
//		jc.setRootClass(UnitedMetricsModel.class);
//		String json1 = net.sf.json.JSONObject.fromObject(m,jc).toString();
//		System.out.println(json1);
//		net.sf.json.JSONObject jo = net.sf.json.JSONObject.fromObject(json1);
//		UnitedMetricsModel m1 = (UnitedMetricsModel)net.sf.json.JSONObject.toBean(jo, UnitedMetricsModel.class);
//		System.out.println(m1);
		
//		Gson gson = new Gson();
//		String json2 = gson.toJson(m);
//		System.out.println(json2);
//		UnitedMetricsModel m2 = gson.fromJson(json2, UnitedMetricsModel.class);
//		System.out.println(m2);
		/*byte[] bs = serialize(m);
		UnitedMetricsModel obj = deserialize(bs);
		
		System.out.println(obj);*/

	}
}
