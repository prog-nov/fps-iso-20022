package com.forms.batch.job.unit.dispatcher;

import java.io.File;

import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;

public class StopDispatcherProcessor extends BatchBaseJob
{
	private File tokenFile = null;
	
	public void init() throws BatchJobException
	{
		tokenFile = new File(this.batchData + DispatcherProcessor.TOKEN_FILE_NAME);
	}
	
	@Override
	public boolean execute() throws BatchJobException
	{
		// CHECK EXCUTED
		if(tokenFile.exists())
		{
			tokenFile.delete();
		}
		return true;
	}
}
