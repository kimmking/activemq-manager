package org.kimmking.am.agent;

import org.kimmking.am.agent.command.Command;
import org.kimmking.am.agent.command.CommandUtils;


/**
 * @author kimmking.cn@gmail.com
 */
public class Agent implements AgentMBean{

	@Override
	public String command(Command cmd) {
		return cmd.execute();
	}

	@Override
	public String execute(String cmd) {
		return CommandUtils.exec(cmd);
	}

}
