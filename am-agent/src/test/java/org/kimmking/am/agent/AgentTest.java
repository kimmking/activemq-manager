package org.kimmking.am.agent;

import java.util.Iterator;
import java.util.Set;

import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class AgentTest {

	public static void main(String[] args) throws Exception {
		 JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/am");       
	        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);        
	        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();      
	        ObjectName mbeanName = new ObjectName("org.kimmking.am:name=Agent");    
	        System.out.println("Domains:---------------");       
	        String domains[] = mbsc.getDomains();        
	        for (int i = 0; i < domains.length; i++) {        
	            System.out.println("/tDomain[" + i + "] = " + domains[i]);     
	        }    
	        System.out.println("MBean count = " + mbsc.getMBeanCount());    
	        //mbsc.setAttribute(mbeanName, new Attribute("Name", "PANDA"));// 设值    
	        AgentMBean proxy = (AgentMBean) MBeanServerInvocationHandler.newProxyInstance(mbsc, mbeanName, AgentMBean.class, false);         
	        String result = proxy.execute("cmd /C dir");   
	        System.out.println( result );  
	        
	        Object r = mbsc.invoke(mbeanName, "execute", new Object[] { "cmd /C ipconfig" }, new String[] { String.class.getName() });   
	        System.out.println( r ); 
	        
	        // 得mbean的信息    
	        MBeanInfo info = mbsc.getMBeanInfo(mbeanName);          
	        System.out.println("Hello Class: " + info.getClassName());       
	        if(info.getAttributes().length > 0)System.out.println("Hello Attriber：" + info.getAttributes()[0].getName());      
	        if(info.getOperations().length > 0)System.out.println("Hello Operation：" + info.getOperations()[0].getName());    
	        // 得到所有的MBean的ObjectName    
	        System.out.println("all ObjectName：---------------");         
	        Set set = mbsc.queryMBeans(null, null);        
	        for (Iterator it = set.iterator(); it.hasNext();) {         
	            ObjectInstance oi = (ObjectInstance) it.next();         
	            System.out.println("/t" + oi.getObjectName());         
	            }
	        jmxc.close();
	}
}
