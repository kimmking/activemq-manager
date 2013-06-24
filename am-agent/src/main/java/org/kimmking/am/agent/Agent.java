package org.kimmking.am.agent;


/**
 * @author kimmking.cn@gmail.com
 */
public class Agent implements AgentMBean{

	@Override
	public String hello(String test) {
		
		return "Hello,"+test;
	}

	

}
