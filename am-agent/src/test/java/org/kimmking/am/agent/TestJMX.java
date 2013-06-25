package org.kimmking.am.agent;

import java.util.Iterator;
import java.util.Set;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class TestJMX {

	// http://activemq.apache.org/using-apache-activemq.html
	public static void main(String[] args) throws Exception {

		String surl = "service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi";

		JMXServiceURL url = new JMXServiceURL(surl);
		JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
		MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
		
		System.out.println("Domains:---------------");
		String domains[] = mbsc.getDomains();
		for (int i = 0; i < domains.length; i++) {
			System.out.println("\tDomain[" + i + "] = " + domains[i]);
		}
		
		System.out.println("all ObjectName£º---------------");
		Set<ObjectInstance> set = mbsc.queryMBeans(null, null);
		for (Iterator<ObjectInstance> it = set.iterator(); it.hasNext();) {
			ObjectInstance oi = (ObjectInstance) it.next();
			System.out.println("\t" + oi.getObjectName());
		}
		
		System.out.println("org.apache.activemq:BrokerName=localhost,Type=Broker£º---------------");
		ObjectName mbeanName = new ObjectName("org.apache.activemq:BrokerName=localhost,Type=Broker");
		MBeanInfo info = mbsc.getMBeanInfo(mbeanName);
		System.out.println("Class: " + info.getClassName());
		if (info.getAttributes().length > 0){
			for(MBeanAttributeInfo m : info.getAttributes())
				System.out.println("\t ==> Attriber£º" + m.getName());
		}
		if (info.getOperations().length > 0){
			for(MBeanOperationInfo m : info.getOperations())
				System.out.println("\t ==> Operation£º" + m.getName());
		}
		
		jmxc.close();

	}

}
