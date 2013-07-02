package org.kimmking.am.agent.command;

import java.util.HashMap;
import java.util.Map;

public class ShellCommand implements Command {

	private CommandType commandType;
	private Object commandBody;
	private Map<String, Object> commandHeader = new HashMap<String, Object>();

	public ShellCommand(CommandType type) {
		this.commandType = type;
	}

	@Override
	public CommandType getCommandType() {
		return commandType;
	}

	@Override
	public Object getCommandBody() {
		return commandBody;
	}

	@Override
	public Map<String, Object> getCommandHeader() {
		return commandHeader;
	}

	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}

	public void setCommandBody(Object commandBody) {
		this.commandBody = commandBody;
	}

	public void setCommandHeader(Map<String, Object> commandHeader) {
		this.commandHeader = commandHeader;
	}

	@Override
	public String execute() {
		return this.getCommandBody() == null ? null : CommandUtils.exec(this.getCommandBody().toString());
	}

}
