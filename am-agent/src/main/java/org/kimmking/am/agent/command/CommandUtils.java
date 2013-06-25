package org.kimmking.am.agent.command;

import java.io.BufferedInputStream;
import java.io.IOException;

public class CommandUtils {

	public static String exec(String cmd) {
		try {
			if (cmd == null)
				return "NONE";
			Process process = Runtime.getRuntime().exec(cmd);
			BufferedInputStream br = new BufferedInputStream(process.getInputStream());
			BufferedInputStream br1 = new BufferedInputStream(process.getErrorStream());
			int ch;
			StringBuffer text = new StringBuffer("result:");
			while ((ch = br.read()) != -1) {
				text.append((char) ch);
			}
			StringBuffer text1 = new StringBuffer("error:");
			while ((ch = br1.read()) != -1) {
				text1.append((char) ch);
			}
			System.out.println(text);
			System.out.println(text1);
			
			return text.toString();

		} catch (IOException e) {
			e.printStackTrace();
			return "e:"+e.getMessage();
		}
	}

	public static boolean isWindows() {
		return true;
	}
	
}
