#RIS日常批量程序
mydate=`date +%Y%m%d`
mytime=`date +'%Y-%m-%d %H:%M:%S'`
myhost=`hostname -s`
_DBNAME="rtcxdb"
_DBUSER="rtcxdbm"
_DBPWD="rtcxrt"
_LOG_FILE=$RIS_BATCH_LOG/RisBatch.log
batchAcDate=`date +%Y-%m-%d`
batchAcDateNoSplit=`date +%Y%m%d`

. $RIS_BATCH_BIN/risdbmanager.sh

#模块功能： 判断文件是否存在
#参数1：    检查文件名
FileNotExist()
{
  #if no param we will believe the file is exist
  _FILE_NAME=$1
  if [ $# = "0" ]
  then
  	echo "参数错误，请检查"
    echo "参数错误，请检查">>${_LOG_FILE}
    return 1
  else
    if [ ! -f $_FILE_NAME ]
    then
      echo "数据文件"$_FILE_NAME"不存在，请联系主机检查是否处理完数据"
      echo "数据文件"$_FILE_NAME"不存在，请联系主机检查是否处理完数据">>${_LOG_FILE}
      return 1
    else
      return 0
    fi
  fi
}

#检查文件
CheckFile()
{
	 checkResult="true"
   echo "检查汇入支付报文信息文件"
   echo "检查汇入支付报文信息文件">>${_LOG_FILE}
   FileNotExist ${RIS_BATCH_ISB}/RIS/IN/0100006D.dq1.gz.${batchAcDateNoSplit}1
   if [ "$?" != "0" ]
   then
     echo "汇入支付报文信息文件没有正常下传，请联系主机检查"
     echo "汇入支付报文信息文件没有正常下传，请联系主机检查">>${_LOG_FILE}
     checkResult=false
   fi

   echo "检查汇入支付报文贷方处理文件"
   FileNotExist ${RIS_BATCH_ISB}/RIS/IN/0175040D.p21.gz.${batchAcDateNoSplit}1
   if [ "$?" != "0" ]
   then
     echo "汇入支付报文贷方处理没有正常下传，请联系主机检查"
     echo "汇入支付报文贷方处理没有正常下传，请联系主机检查">>${_LOG_FILE}
     checkResult=false
   fi
   
   echo "检查汇出报文数据"
   FileNotExist ${RIS_BATCH_ISB}/RIS/IN/0175040D.p31.gz.${batchAcDateNoSplit}1
   if [ "$?" != "0" ]
   then
     echo "汇出报文数据没有正常下传，请联系主机检查"
     echo "汇出报文数据没有正常下传，请联系主机检查">>${_LOG_FILE}
     checkResult=false
   fi

   echo "检查支付报文历史交易数据"
   FileNotExist ${RIS_BATCH_ISB}/RIS/IN/0100006D.ec1.gz.${batchAcDateNoSplit}1
   if [ "$?" != "0" ]
   then
     echo "支付报文历史交易数据没有正常下传，请联系主机检查"
     echo "支付报文历史交易数据没有正常下传，请联系主机检查">>${_LOG_FILE}
     checkResult=false
   fi
   
   if [ checkResult = "false" ]
   then
   	return 1
   else
   	return 0
   fi
}

mainApp()
{
	exedate=$1
	if [ ! -z $exedate ]
	then
		batchAcDate=$exedate
		batchAcDateNoSplit=`echo $batchAcDate|cut -f 1 -d -``echo $batchAcDate|cut -f 2 -d -``echo $batchAcDate|cut -f 3 -d -`
	fi
	echo "batchAcDate=$batchAcDate"
 	echo "batchAcDateNoSplit=$batchAcDateNoSplit">>${_LOG_FILE}
	
	CheckFile
	if [ "$?" != "0" ]
	then
		return 1
	fi
	
	#第1步 汇入报文信息数据录入中间表
	echo "第1步 汇入报文信息数据录入中间表"
	echo "第1步 汇入报文信息数据录入中间表">>${_LOG_FILE}
	echo "sh risdq1.sh -D $batchAcDate"
	echo "sh risdq1.sh -D $batchAcDate">>${_LOG_FILE}
	sh risdq1.sh -D $batchAcDate
	if [ "$?" != "0" ]
	then
		echo "第1步 汇入报文信息数据录入中间表失败，请检查"
   	echo "第1步 汇入报文信息数据录入中间表失败，请检查">>${_LOG_FILE}
   	return 1
	fi
	
	#第2步 汇入报文贷方处理表录入中间表
	echo "第2步 汇入报文贷方处理表录入中间表"
	echo "第2步 汇入报文贷方处理表录入中间表">>${_LOG_FILE}
	echo "sh risp21.sh -D $batchAcDate"
	echo "sh risp21.sh -D $batchAcDate">>${_LOG_FILE}
	sh risp21.sh -D $batchAcDate
	if [ "$?" != "0" ]
	then
		echo "第2步 汇入报文贷方处理表录入中间表失败，请检查"
   	echo "第2步 汇入报文贷方处理表录入中间表失败，请检查">>${_LOG_FILE}
   	return 1
	fi
	
	#第3步 汇出报文信息录入中间表
	echo "第3步 汇出报文信息录入中间表"
	echo "第3步 汇出报文信息录入中间表">>${_LOG_FILE}
	echo "sh risp31.sh -D $batchAcDate"
	echo "sh risp31.sh -D $batchAcDate">>${_LOG_FILE}
	sh risp31.sh -D $batchAcDate
	if [ "$?" != "0" ]
	then
		echo "第3步 汇出报文信息录入中间表失败，请检查"
   	echo "第3步 汇出报文信息录入中间表失败，请检查">>${_LOG_FILE}
   	return 1
	fi
	
	#第4步 汇入支付报文录入数据主表
	echo "第4步 汇入支付报文录入数据主表"
	echo "第4步 汇入支付报文录入数据主表">>${_LOG_FILE}
	echo "sh risdealpay.sh -D $batchAcDate"
	echo "sh risdealpay.sh -D $batchAcDate">>${_LOG_FILE}
	sh risdealpay.sh -D $batchAcDate
	if [ "$?" != "0" ]
	then
		echo "第4步 汇入支付报文录入数据主表失败，请检查"
   	echo "第4步 汇入支付报文录入数据主表失败，请检查">>${_LOG_FILE}
   	return 1
	fi
	
	#第5步 查询报文信息录入数据主表
	echo "第5步 查询报文信息录入数据主表"
	echo "第5步 查询报文信息录入数据主表">>${_LOG_FILE}
	echo "sh risdealmoverseas.sh -D $batchAcDate"
	echo "sh risdealmoverseas.sh -D $batchAcDate">>${_LOG_FILE}
	sh risdealmoverseas.sh -D $batchAcDate
	if [ "$?" != "0" ]
	then
		echo "第6步 查询报文信息录入数据主表失败，请检查"
   	echo "第6步 查询报文信息录入数据主表失败，请检查">>${_LOG_FILE}
   	return 1
	fi
	
	#第6步 汇出报文信息录入数据主表
	echo "第6步 汇出报文信息录入数据主表"
	echo "第6步 汇出报文信息录入数据主表">>${_LOG_FILE}
	echo "sh risdealpayout.sh -D $batchAcDate"
	echo "sh risdealpayout.sh -D $batchAcDate">>${_LOG_FILE}
	sh risdealpayout.sh -D $batchAcDate
	if [ "$?" != "0" ]
	then
		echo "第6步 汇出报文信息录入数据主表失败，请检查"
   	echo "第6步 汇出报文信息录入数据主表失败，请检查">>${_LOG_FILE}
   	return 1
	fi
	
	#第7步 支付报文交易历史数据录入
	echo "第7步 支付报文交易历史数据录入"
	echo "第7步 支付报文交易历史数据录入">>${_LOG_FILE}
	echo "sh risec1.sh -D $batchAcDate"
	echo "sh risec1.sh -D $batchAcDate">>${_LOG_FILE}
	sh risec1.sh -D $batchAcDate
	if [ "$?" != "0" ]
	then
		echo "第7步 支付报文交易历史数据录入失败，请检查"
   	echo "第7步 支付报文交易历史数据录入失败，请检查">>${_LOG_FILE}
   	return 1
	fi
	
	#第8步 更新汇入支付报文退报我方编号
	echo "第8步 更新汇入支付报文退报我方编号"
	echo "第8步 更新汇入支付报文退报我方编号">>${_LOG_FILE}
	echo "sh risdealbackourno.sh -D $batchAcDate"
	echo "sh risdealbackourno.sh -D $batchAcDate">>${_LOG_FILE}
	sh risdealbackourno.sh -D $batchAcDate
	if [ "$?" != "0" ]
	then
		echo "第8步 更新汇入支付报文退报我方编号失败，请检查"
   	echo "第8步 更新汇入支付报文退报我方编号失败，请检查">>${_LOG_FILE}
   	return 1
	fi
	
	#第9步 自动勾连建档
	echo "第9步 自动勾连建档"
	echo "第9步 自动勾连建档">>${_LOG_FILE}
	echo "sh risdealarchive.sh -D $batchAcDate"
	echo "sh risdealarchive.sh -D $batchAcDate">>${_LOG_FILE}
	sh risdealarchive.sh -D $batchAcDate
	if [ "$?" != "0" ]
	then
		echo "第9步 自动勾连建档失败，请检查"
   	echo "第9步 自动勾连建档失败，请检查">>${_LOG_FILE}
   	return 1
	fi
	
	#第10步 清理汇入支付报文信息
	echo "第10步 清理汇入支付报文信息"
	echo "第10步 清理汇入支付报文信息">>${_LOG_FILE}
	echo "sh rishuspayin.sh -D $batchAcDate"
	echo "sh rishuspayin.sh -D $batchAcDate">>${_LOG_FILE}
	sh rishuspayin.sh -D $batchAcDate
	if [ "$?" != "0" ]
	then
		echo "第10步 清理汇入支付报文信息失败，请检查"
   	echo "第10步 清理汇入支付报文信息失败，请检查">>${_LOG_FILE}
   	return 1
	fi
	
	#第11步 清理汇出支付报文信息
	echo "第11步 清理汇出支付报文信息"
	echo "第11步 清理汇出支付报文信息">>${_LOG_FILE}
	echo "sh rishuspayout.sh -D $batchAcDate"
	echo "sh rishuspayout.sh -D $batchAcDate">>${_LOG_FILE}
	sh rishuspayout.sh -D $batchAcDate
	if [ "$?" != "0" ]
	then
		echo "第11步 清理汇出支付报文信息失败，请检查"
   	echo "第11步 清理汇出支付报文信息失败，请检查">>${_LOG_FILE}
   	return 1
	fi
	
	#第12步 清理查询报文信息
	echo "第12步 清理查询报文信息"
	echo "第12步 清理查询报文信息">>${_LOG_FILE}
	echo "sh rishusmoverseas.sh -D $batchAcDate"
	echo "sh rishusmoverseas.sh -D $batchAcDate">>${_LOG_FILE}
	#sh rishusmoverseas.sh -D $batchAcDate
	#if [ "$?" != "0" ]
	#then
	#	echo "第12步 清理查询报文信息失败，请检查"
  # 	echo "第12步 清理查询报文信息失败，请检查">>${_LOG_FILE}
  #	return 1
	#fi
	
	#第13步 清理中间表
	echo "第13步 清理中间表"
	echo "第13步 清理中间表">>${_LOG_FILE}
	echo "sh rishustemptable.sh -D $batchAcDate"
	echo "sh rishustemptable.sh -D $batchAcDate">>${_LOG_FILE}
	sh rishustemptable.sh -D $batchAcDate
	if [ "$?" != "0" ]
	then
		echo "第13步 清理中间表失败，请检查"
   	echo "第13步 清理中间表失败，请检查">>${_LOG_FILE}
   	return 1
	fi
	
	#第14步 重整数据
	echo "第14步 重整数据"
	echo "第14步 重整数据">>${_LOG_FILE}
	echo "ReorgDB"
	echo "ReorgDB">>${_LOG_FILE}
	ReorgDB
	if [ "$?" != "0" ]
	then
		echo "第14步 重整数据失败，请检查"
   	echo "第14步 重整数据失败，请检查">>${_LOG_FILE}
   return 1
	fi
	
	#第15步 备份当日数据主档
	echo "第15步 备份当日数据主档"
	echo "第15步 备份当日数据主档">>${_LOG_FILE}
	echo "BackupDb"
	echo "BackupDb">>${_LOG_FILE}
	BackupDb
	if [ "$?" != "0" ]
	then
		echo "第15步 备份当日数据主档失败，请检查"
   	echo "第15步 备份当日数据主档失败，请检查">>${_LOG_FILE}
   return 1
	fi
}

mainApp $1
