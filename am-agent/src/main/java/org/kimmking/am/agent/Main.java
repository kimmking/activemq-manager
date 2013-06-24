package org.kimmking.am.agent;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

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
			System.out.println("start.....");

	}

}
