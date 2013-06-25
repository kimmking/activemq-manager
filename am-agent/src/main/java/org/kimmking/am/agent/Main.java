package org.kimmking.am.agent;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXAuthenticator;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXPrincipal;
import javax.management.remote.JMXServiceURL;
import javax.security.auth.Subject;

import com.sun.jdmk.comm.HtmlAdaptorServer;

/**
 * @author kimmking.cn@gmail.com
 */
public class Main {

	public static void main(String[] args) throws Exception {

			MBeanServer server = MBeanServerFactory.createMBeanServer();
			
			ObjectName helloName = new ObjectName("org.kimmking.am:name=Agent");
			server.registerMBean(new Agent(), helloName);

			ObjectName adapterName = new ObjectName("org.kimmking.am:name=htmladapter,port=8082");
			HtmlAdaptorServer adapter = new HtmlAdaptorServer();
			server.registerMBean(adapter, adapterName);

			adapter.start();
			System.out.println("start HtmlAdaptorServer.....");
			
			LocateRegistry.createRegistry(9999);   
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/am"); 

            Map<String,Object> env = new HashMap<String,Object>();   
            JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(url, env, server);    
  
            cs.start();   
            System.out.println("rmi 9999 start.....");   
	}

}
