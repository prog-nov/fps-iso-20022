package com.forms.framework.util;

import com.forms.framework.exception.BatchJobException;

public class ExecCmdUtil
{

	public static void execCmd(String[] cmd) throws BatchJobException
	{
		try
		{
			Process process = Runtime.getRuntime().exec(cmd);
			ExecThread th1 = new ExecThread(process.getInputStream());
			th1.start();
			ExecThread th2 = new ExecThread(process.getErrorStream());
			th2.start();
			int result = process.waitFor();
			if (result != 0)
			{
				throw new BatchJobException("exec cmd fail");
			}
		} catch (Exception e)
		{
			throw new BatchJobException(e);
		}
	}

}
