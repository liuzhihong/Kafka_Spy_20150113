package com.suning.app.spy.core.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 类JmxUtil.java的实现描述： JVMTI加载Agent实现本地JMX
 * 
 * @author karry 2014-11-12 上午10:22:20
 */
public abstract class JmxUtil {

	private static final String CONNECTOR_ADDRESS = "com.sun.management.jmxremote.localConnectorAddress";

	private static String JAVA_HOME = System.getProperty("java.home");
	
	private static String JVM_SUPPLIER = System.getProperty("java.vm.specification.vendor");

	private static final String CLASS_VIRTUAL_MACHINE = "com.sun.tools.attach.VirtualMachine";

	private static final String CLASS_VIRTUAL_MACHINE_DESCRIPTOR = "com.sun.tools.attach.VirtualMachineDescriptor";

	private static final String CLASS_JMX_REMOTE = "com.sun.management.jmxremote";
	
	private static final Logger LOGGER = Logger.getLogger(JmxUtil.class);
	
	private static  URLClassLoader classLoader;
	
	static {
		
		try {
			classLoader = new URLClassLoader(new URL[] { getToolsJar().toURI().toURL() });
		} catch (MalformedURLException e) {
			LOGGER.error("Init JmxUtil error where classLoader set error!");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String findJMXUrlByProcessId(int pid) {

		if (!isSunJVM() || null == classLoader) {
			return StringHelper.EMPTY_STRING;
		}

		String connectorAddress = StringHelper.EMPTY_STRING;
		
		Object targetVm = null;
		Method attachToVM = null;
		Method detach = null;

		try {

			Class virtualMachine = Class.forName(CLASS_VIRTUAL_MACHINE, true, classLoader);
			Class virtualMachineDescriptor = Class.forName(CLASS_VIRTUAL_MACHINE_DESCRIPTOR, true, classLoader);

			Method getVMList = virtualMachine.getMethod("list", (Class[]) null);
			attachToVM = virtualMachine.getMethod("attach", String.class);
			detach = virtualMachine.getMethod("detach", (Class[]) null);
			Method getAgentProperties = virtualMachine.getMethod("getAgentProperties", (Class[]) null);
			Method getVMId = virtualMachineDescriptor.getMethod("id", (Class[]) null);
			

			List allVMs = (List) getVMList.invoke(null, (Object[]) null);

			for (Object vmInstance : allVMs) {
				String id = (String) getVMId.invoke(vmInstance, (Object[]) null);
				if (id.equals(Integer.toString(pid))) {

					try {
                        targetVm = attachToVM.invoke(null, id);
                    } catch (Exception e) {
                        LOGGER.error("Attach PID "+id+" VM Failed !", e);
                    }

					Properties agentProperties = (Properties) getAgentProperties.invoke(targetVm, (Object[]) null);
					connectorAddress = agentProperties.getProperty(CONNECTOR_ADDRESS);
					break;
				}
			}

			if (StringHelper.isBlank(connectorAddress)) {
				// 尝试让agent加载management-agent.jar
				Method loadAgent = virtualMachine.getMethod("loadAgent", String.class, String.class);
				
				for (Object vmInstance : allVMs) {
					String id = (String) getVMId.invoke(vmInstance, (Object[]) null);
					if (id.equals(Integer.toString(pid))) {

						targetVm = attachToVM.invoke(null, id);

						File agentJar = getAgentJar();
						if (null == agentJar) {
							throw new IOException("Management agent Jar not found");
						}

						String agent = agentJar.getCanonicalPath();
						loadAgent.invoke(targetVm, agent, CLASS_JMX_REMOTE);

						Properties agentProperties = (Properties) getAgentProperties.invoke(targetVm, (Object[]) null);
						connectorAddress = agentProperties.getProperty(CONNECTOR_ADDRESS);

						break;
					}
				}
			}

		} catch (Exception ignore) {
			System.err.println(ignore);
		}finally {
			if (null != targetVm && null != detach) {
				try {
					detach.invoke(targetVm, (Object[]) null);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}

		return connectorAddress;
	}

	private static File getToolsJar() {
		String tools = JAVA_HOME + File.separator + "lib" + File.separator + "tools.jar";
		File f = new File(tools);
		if (!f.exists()) {
			tools = JAVA_HOME + File.separator + ".." + File.separator + "lib" + File.separator + "tools.jar";
			f = new File(tools);
		}
		return f;
	}

	private static File getAgentJar() {
		String agent = JAVA_HOME + File.separator + "jre" + File.separator + "lib" + File.separator + "management-agent.jar";
		File f = new File(agent);
		if (!f.exists()) {
			agent = JAVA_HOME + File.separator + "lib" + File.separator + "management-agent.jar";
			f = new File(agent);
			if (!f.exists()) {
				return null;
			}
		}
		return f;
	}

	private static boolean isSunJVM() {
		return JVM_SUPPLIER.equals("Sun Microsystems Inc.") || JVM_SUPPLIER.startsWith("Oracle");
	}

	public static void main(String[] args) throws IOException {
		byte[] buff = new byte[1024];
		while(System.in.read(buff) > 0) {
			String pid = new String(buff);			
			String jmxUrl = findJMXUrlByProcessId(Integer.parseInt(pid.substring(0, pid.lastIndexOf("\n"))));
			System.out.println(jmxUrl);
			
		}
		
	}
}