waitEcgLog()
{	
	logType=$1
	logMsg="$2"
	
	if [ $logType == 1 ]
	then
		echo [ERRO] [`date +"%Y/%m/%d %H:%M:%S`]  [ShellJob  $logMsg] 
		echo [ERRO] [`date +"%Y/%m/%d %H:%M:%S`]  [ShellJob  $logMsg] >>$RIS_BATCH_LOG/RisError.log
	elif [ $logType == 2 ]
	then
		echo [WARN] [`date +"%Y/%m/%d %H:%M:%S`]  [ShellJob  $logMsg]
		echo [WARN] [`date +"%Y/%m/%d %H:%M:%S`]  [ShellJob  $logMsg] >>$RIS_BATCH_LOG/RisWarn.log
	else
		echo [INFO] [`date +"%Y/%m/%d %H:%M:%S`]  [ShellJob  $logMsg]
		echo [INFO] [`date +"%Y/%m/%d %H:%M:%S`]  [ShellJob  $logMsg] >>$RIS_BATCH_LOG/RisInfo.log
	fi
}