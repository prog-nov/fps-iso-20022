waitEcgLog()
{	
	logType=$1
	logMsg="$2"
	
	if [ $logType == 1 ]
	then
		echo [ERRO] [`date +"%Y/%m/%d %H:%M:%S`] [CIB-CMN0001CHK] [ShellJob  $logMsg] 
		echo [ERRO] [`date +"%Y/%m/%d %H:%M:%S`] [CIB-CMN0001CHK] [ShellJob  $logMsg] >>$BOCCIB/log/CibError.log
	elif [ $logType == 2 ]
	then
		echo [WARN] [`date +"%Y/%m/%d %H:%M:%S`] [CIB-CMN0001CHK] [ShellJob  $logMsg]
		echo [WARN] [`date +"%Y/%m/%d %H:%M:%S`] [CIB-CMN0001CHK] [ShellJob  $logMsg] >>$BOCCIB/log/CibWarn.log
	else
		echo [INFO] [`date +"%Y/%m/%d %H:%M:%S`] [CIB-CMN0001CHK] [ShellJob  $logMsg]
		echo [INFO] [`date +"%Y/%m/%d %H:%M:%S`] [CIB-CMN0001CHK] [ShellJob  $logMsg] >>$BOCCIB/log/CibInfo.log
	fi
}