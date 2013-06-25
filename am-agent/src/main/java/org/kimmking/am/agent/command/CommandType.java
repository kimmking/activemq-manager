package org.kimmking.am.agent.command;

public enum CommandType {
	
	CMD,        // execute a Command in the agent, ignore platforms
	ShellLocal, // execute a local shell script in the agent 
	ShellRomete, // execute a shell command from this Call args
	NOOP         // nothing but keep alive
	
}
