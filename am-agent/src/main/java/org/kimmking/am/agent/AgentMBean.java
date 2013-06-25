package org.kimmking.am.agent;

import org.kimmking.am.agent.command.Command;

public interface AgentMBean {

	String command(Command cmd);
	
	String execute(String cmd);
}
