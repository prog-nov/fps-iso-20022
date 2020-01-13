package com.forms.framework;

public class BatchJobConstants
{
	// Batch default path
	public static final String DEFAULT_SYS_CONFIG_FILE = "SystemConfig";

	public static final String JOB_FREQUENCY_D = "D";

	public static final String JOB_FREQUENCY_A = "A";

	public static final String JOB_FREQUENCY_M = "M";

	public static final String JOB_FREQUENCY_S = "S";

	public static final String JOB_FREQUENCY_H = "H";

	public static final String JOB_FREQUENCY_Y = "Y";

	public static final String JOB_FREQUENCY_N = "N";

	// Job type
	public static final String JOB_ACTION_TYPE_JAVASYSCALL = "JavaSysCall";

	public static final String JOB_ACTION_TYPE_JAVAACTION = "JavaAction";

	public static final String JOB_ACTION_TYPE_DATAPIP = "DataPip";

	public static final String JOB_ACTION_TYPE_SYSCALL = "SysCall";
	
	public static final String JOB_ACTION_TYPE_CMDACTION = "CMDAction";
	
	public static final String JOB_ACTION_TYPE_OPRMAN = "OprMan";
	
	// Job Parameter Type
	public static final String JOB_PARAMETER_CLASS = "PARSEJOBPARASCLASS";
	// rerun from begin
	public static final String JOB_PARAMETER_TYPE_RUNFLAG = "-R";
	// -D BATCH ACCOUNT DATE SETTING
	public static final String JOB_PARAMETER_TYPE_BATCHACDATE = "-D";
	// -S SERVER DATE SETTING
	public static final String JOB_PARAMETER_TYPE_SERVER = "-S";
	// -P PARAMETER REPLACE SETTING, eg: twoAcDateAgo=2012-01-02
	public static final String JOB_PARAMETER_TYPE_PARAMETER = "-P";

	// Job execute stat
	public static final String JOB_STATUS_PROCESSING = "P";

	public static final String JOB_STATUS_END = "E";

	// Job execute result
	public static final String JOB_RESULT_SUCCESS = "S";

	public static final String JOB_RESULT_FAIL = "F";

	public static final String JOB_RESULT_SKIP = "K";

	// rerun flag
	public static final String JOB_RERUN_UNNEEDED = "UNNEEDED";

	public static final String JOB_RERUN_NEEDED = "NEEDED";

	public static final String JOB_RERUN_MANDATORY = "MANDATORY";

	// rerun type
	public static final String JOB_RERUN_TYPE_BREAKPOINT = "breakpoint";

	public static final String JOB_RERUN_TYPE_FROMBEGIN = "frombegin";

	// File Date Check Type
	public static final String FILE_VALIDATE_TYPE_BATCH="BATCH";
	
	public static final String FILE_VALIDATE_TYPE_ONLINE="ONLINE";
	
	public static final String FILE_VALIDATE_TYPE_SYSTEM="SYSTEM";
	
	public static final String FILE_VALIDATE_TYPE_N="N";
	
	public static final String ACTION_CONDITION_TYPE_SQL = "SQLCONDITION";
	
}
