package com.forms.framework.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ExecThread extends Thread
{
	InputStream is = null;

	public ExecThread(InputStream is)
	{
		this.is = is;
	}

	public void run()
	{
		InputStreamReader isr = null;
		BufferedReader br = null;
		try
		{
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			while (br.readLine() != null)
			{				
				Thread.sleep(10);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (br != null)
				{
					br.close();
				}
				if (isr != null)
				{
					isr.close();
				}
				if (is != null)
				{
					is.close();
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
