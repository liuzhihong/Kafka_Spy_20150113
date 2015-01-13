package com.suning.shared.spy.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.suning.shared.spy.enums.MetricsType;
import com.suning.shared.spy.model.biz.AggregateGCInfoModel;
import com.suning.shared.spy.model.biz.CpuInfoModel;
import com.suning.shared.spy.model.biz.GCInfoModel;
import com.suning.shared.spy.model.biz.NetInfoModel;
import com.suning.shared.spy.model.biz.VmMemInfoModel;

/**
 * 类UnitedMetricsModel.java的实现描述
 * 
 * @author karry 2014-10-29 下午2:35:47
 */
public class TmpMetricsModel implements Serializable {

	private static final long serialVersionUID = -2283555842100914153L;

	private Map<String, MetricsInfoModel> metricsModels = new HashMap<String, MetricsInfoModel>(MetricsType.size());

	private MetricsTarget metricsTarget;

	public Map<String, MetricsInfoModel> getMetricsModels() {
		return metricsModels;
	}
	
	public MetricsTarget getMetricsTarget() {
		return this.metricsTarget;
	}

	// //////////////////write//////////////////////

	public TmpMetricsModel() {

	}

	public TmpMetricsModel(MetricsTarget metricsTarget) {
		this.metricsTarget = metricsTarget;
	}

	public void setMetricsModel(MetricsInfoModel model) {
		metricsModels.put(model.getType().getName(), model);
	}
	
	public void setMetricsTarget(MetricsTarget metricsTarget) {
		this.metricsTarget = metricsTarget;
	}

	public void clear() {
		metricsModels.clear();
	}

	public int size() {
		return metricsModels.size();
	}

	@Override
	public String toString() {
		return this.metricsTarget.getAppName() + "/" + this.metricsTarget.getClusterName() + "/" + this.metricsTarget.getIp();
	}
	
	public static void main(String[] args) throws Exception {
		TmpMetricsModel m = new TmpMetricsModel(new MetricsTarget("dddd","eeee","fffff"));
		
		m.setMetricsModel(new CpuInfoModel());
		m.setMetricsModel(new NetInfoModel());
		m.setMetricsModel(new VmMemInfoModel());
		
		AggregateGCInfoModel aggregateGCInfoModel = new AggregateGCInfoModel();
		
		Map<String, GCInfoModel> map = new HashMap<String, GCInfoModel>();
		GCInfoModel gcInfoModel = new GCInfoModel();
		gcInfoModel.setFullGCCount(5);
		map.put("Hello", gcInfoModel);
		aggregateGCInfoModel.setExtGcInfo(map);
		
		GCInfoModel selfGc = new GCInfoModel();
		selfGc.setFullGCCount(10);
		aggregateGCInfoModel.setSelfGcInfo(selfGc);
		
		m.setMetricsModel(aggregateGCInfoModel);
		
//		String json = JSON.toJSONString(m, SerializerFeature.WriteClassName);
//		TransportMetricsModel n = JSON.parseObject(json, TransportMetricsModel.class);
		
//		SpyUnitedMetricsModel.setMetricsModel(n);
//		System.out.println(SpyUnitedMetricsModel.hasGCInfo());
//		System.out.println(SpyUnitedMetricsModel.getGCInfoModel().getSelfGcInfo().getFullGCCount());
//		System.out.println(SpyUnitedMetricsModel.getGCInfoModel().getExtGcInfo().get("Hello").getFullGCCount());
//		AggregateGCInfoModel a1 = n.getGCInfoModel();
//		GCInfoModel g1 = a1.getExtGcInfo().get("Kafka");
//		System.out.println(g1.getFullGCCount());
		
		/*String json2 = JSON.toJSONString(n.getMetricsModels(),SerializerFeature.WriteClassName);
		Map<String, MetricsInfoModel> maps = JSON.parseObject(json2,new TypeReference<Map<String,MetricsInfoModel>>(){});
		System.out.println(maps);*/
		
		
//		System.out.println(n.getGCInfoModel().getExtGcInfo().get("Kafka").getFullGCCount());
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
	/*public static byte[] serialize(Object obj){ 
		ByteArrayOutputStream bos = null;
		ObjectOutputStream out = null;
		byte[] bytes = null;
		try {
			bos = new ByteArrayOutputStream();
			out = new ObjectOutputStream(bos);
			out.writeObject(obj);
			bytes = bos.toByteArray();
		}catch (IOException e) {
			
		}finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		
		return bytes;
	}
	
	public static UnitedMetricsModel deserialize(byte[] bytes) throws IOException, ClassNotFoundException{   
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInputStream in = new ObjectInputStream(bis);
		
		Object obj=in.readObject();             
		in.close();
		bis.close();

		return (UnitedMetricsModel)obj;
	}*/   	
}
