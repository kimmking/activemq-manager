package org.kimmking.am.agent.command;

import java.util.Map;

public interface Command {
	
	CommandType getCommandType();
	Object getCommandBody();
	Map<String,Object> getCommandHeader();
	String execute();
	
}
