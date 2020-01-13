package com.forms.framework.job;

import java.io.File;

import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.util.CommonAPI;

public abstract class DelDbBackupFileJob extends BatchBaseJob
{

	@Override
	public boolean execute() throws BatchJobException
	{
		// TODO 自動產生方法 Stub
//		delete 7 acdate data
		String backfilePath = this.batchBackup;
		File file=new File(backfilePath);
		File[] files=file.listFiles();
		for(File f:files)
		{
			if(!f.getName().equals(CommonAPI.PROPERTY_PATH)&&f.getName().compareTo(this.getDelDate())<=0)
			{
				this.deleteFile(f);
			}
		}
		return false;
	}

	/**
	 * if name of Directory in BATCH_DBBACKUP_PATH compareTo this
	 * return date 
	 * @return String
	 */
	public abstract String getDelDate();
	
	private void deleteFile(File file)
	{
		if(file.isDirectory())
		{
			File[] files=file.listFiles();
			for(File f:files)
			{
				this.deleteFile(f);
			}
			file.delete();
		}
		else
		{
			file.delete();
		}
	}
}
