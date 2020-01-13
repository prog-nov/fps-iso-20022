#list menu
mydate=`date +%Y%m%d`
mytime=`date +'%Y-%m-%d %H:%M:%S'`
myhost=`hostname -s`
_DBNAME="rtcxdb"
_DBUSER="rtcxdbm"
_DBPWD="rtcxrt"
DBMANAGER_LOG_FILE=$RIS_BATCH_LOG/DBManager.log
WeekAgo=""
CurrDay=""
YearAgo=""
CurrYear=""
AgoYear=""

#模块功能：连接数据库
ConnectDB()
{
  db2 connect to $_DBNAME user ${_DBUSER} using ${_DBPWD}
  if [ $? != 0 ]
  then
    echo "连接数据库失败，请与数据库管理员联系"
    echo "连接数据库失败，请与数据库管理员联系">>${DBMANAGER_LOG_FILE}
    return 1    
  else
    echo "连接数据库成功"
    echo "连接数据库成功">>${DBMANAGER_LOG_FILE}
    return 0
  fi
}

#断开数据库连接
DisconnectDB()
{
  db2 disconnect $_DBNAME
  echo "断开数据库联接$_DBNAME"
  echo "断开数据库联接$_DBNAME">>${DBMANAGER_LOG_FILE}
}

#检查数据库表状态
CheckDbStatus()
{
	ConnectDB
	if [ $? = "0" ]
	then
  	echo "检查数据库表状态,如果发现有表状态异常提示,请联系维护人员,正常情况下无提示"
  	for tbname in `db2 -X "select name as tablename from sysibm.systables where creator = 'RTCXU01' and TYPE = 'T' and STATUS <> 'N' ORDER BY NAME"` ;do echo "表状态"${tbname}"不正常" && echo "表状态"${tbname}"不正常">>${DBMANAGER_LOG_FILE};done
		DisconnectDB
	fi
}

#重整数据
ReorgDB()
{
	ConnectDB
	echo $?
	if [ $? = "0" ]
	then
   	echo "重整所有数据表数据"
   	echo "重整所有数据表数据">>${DBMANAGER_LOG_FILE}
   	for tbname in `db2 -x "select name from sysibm.systables where creator = 'RTCXU01' and type = 'T' ORDER BY NAME "`;do echo "${mydate}重整数据表${tbname}数据"  && db2 "REORG  TABLE RTCXU01.${tbname} ALLOW READ ACCESS " >> ${DBMANAGER_LOG_FILE};done
   	echo "重整所有索引信息"
   	echo "重整所有索引信息">>${DBMANAGER_LOG_FILE}
   	for tbname in `db2 -x "select name from sysibm.systables where creator = 'RTCXU01' and type = 'T' ORDER BY NAME "`;do echo "${mydate}重整数据表${tbname}索引"  && db2 "REORG  INDEXES ALL FOR TABLE RTCXU01.${tbname} ALLOW READ ACCESS " >> ${DBMANAGER_LOG_FILE};done
   	echo "运行所有表的统计信息"
   	echo "运行所有表的统计信息">>${DBMANAGER_LOG_FILE}
   	for tbname in `db2 -x "select name from sysibm.systables where creator = 'RTCXU01' and type = 'T' ORDER BY NAME "`;do echo "${mydate}重整数据表${tbname}索引"  && db2 " RUNSTATS ON TABLE RTCXU01.${tbname} ON KEY COLUMNS  WITH DISTRIBUTION ON ALL COLUMNS  AND DETAILED INDEX ALL  ALLOW READ ACCESS " >> ${DBMANAGER_LOG_FILE};done 
   	DisconnectDB
	return 0
  fi
}

#获取当前时间
GetDBDate()
{
OUT_FILE="${RIS_BATCH_DBBACKUP_PATH}/DATE${mydate}.dat"
echo ${OUT_FILE}
echo "获取系统所需日期"
echo "获取系统所需日期">>${DBMANAGER_LOG_FILE}
ConnectDB
db2 "export to $OUT_FILE of del  MODIFIED BY CHARDEL#  values(Replace(CHAR(Current Date - 7 days,ISO), '-','') || '#' || Replace(CHAR(Current Date,ISO), '-','') || '#' || CHAR(CURRENT DATE - 1 YEAR,ISO) || '#' || (SELECT COALESCE(MAX(RIGHT(OREF_NO,5)),'00000')  FROM RTCXU01.D_RTS_HDUI WHERE SUBSTR(OREF_NO,6,6) = RIGHT(REPLACE(CAST(CURRENT DATE AS CHAR(10)),'-',''),6)))">> ${DBMANAGER_LOG_FILE}
DisconnectDB

if [ "$?" \> "2" ]
then
  echo "获取系统时间失败"
  echo "获取系统时间失败">>${DBMANAGER_LOG_FILE}
  DisconnectDB
  exit
fi

#read file
exec 5<&0 0<$OUT_FILE
read line1
exec 0<&5
WeekAgo=`echo "$line1"|awk -F '#' '{print   $2}' `
CurrDay=`echo "$line1"|awk -F '#' '{print   $4}' `
YearAgo=`echo "$line1"|awk -F '#' '{print   $6}' `
MaxNo=`echo "$line1"|awk -F '#' '{print   $8}' `
CurrYear=`expr substr "${CurrDay}" 1 4`
AgoYear=`expr substr "${YearAgo}" 1 4`
echo "一个星期前日期："${WeekAgo}
echo "数据日期："${CurrDay}
echo "一年前日期："${YearAgo}
echo "当前年年份："${CurrYear}
echo "去年年份:"${AgoYear}
}

#备份数据库
BackupDb()
{
	GetDBDate
	ConnectDB
	if [ $? = "0" ]
	then
   echo "备份来报主档表"
   echo "备份来报主档表">>${DBMANAGER_LOG_FILE}
   db2 "EXPORT TO ${RIS_BATCH_DBBACKUP_PATH}/M_OVERSEAS_RECV${mydate}.IXF OF IXF SELECT * FROM RTCXU01.M_OVERSEAS_RECV " >>${DBMANAGER_LOG_FILE}
   if [ "$?" \> "2" ]
   then
    echo "备份来报主档表失败,请查看日志${DBMANAGER_LOG_FILE}"
    echo "备份来报主档表失败,请查看日志${DBMANAGER_LOG_FILE}">>${DBMANAGER_LOG_FILE}
    return 1
   fi  

   echo "备份来报主档表"
   echo "备份来报主档表">>${DBMANAGER_LOG_FILE}
   db2 "EXPORT TO ${RIS_BATCH_DBBACKUP_PATH}/M_OVERSEAS_SEND${mydate}.IXF OF IXF SELECT * FROM RTCXU01.M_OVERSEAS_SEND " >>${DBMANAGER_LOG_FILE}
   if [ "$?" \> "2" ]
   then
    echo "备份往报主档表失败,请查看日志${DBMANAGER_LOG_FILE}"
    echo "备份往报主档表失败,请查看日志${DBMANAGER_LOG_FILE}">>${DBMANAGER_LOG_FILE}
    return 1
   fi  

   echo "备份内部报文主档表"
   echo "备份内部报文主档表">>${DBMANAGER_LOG_FILE}
   db2 "EXPORT TO ${RIS_BATCH_DBBACKUP_PATH}/M_OVERSEAS_WITHIN${mydate}.IXF OF IXF SELECT * FROM RTCXU01.M_OVERSEAS_WITHIN " >>${DBMANAGER_LOG_FILE}
   if [ "$?" \> "2" ]
   then
    echo "备份内部报文主档表失败,请查看日志${DBMANAGER_LOG_FILE}"
    echo "备份内部报文主档表失败,请查看日志${DBMANAGER_LOG_FILE}">>${DBMANAGER_LOG_FILE}
    return 1
   fi  
   
   echo "备份档案主档表"
   echo "备份档案主档表">>${DBMANAGER_LOG_FILE}
   db2 "EXPORT TO ${RIS_BATCH_DBBACKUP_PATH}/T_OVERSEAS_ARCHIVES${mydate}.IXF OF IXF SELECT * FROM RTCXU01.T_OVERSEAS_ARCHIVES " >>${DBMANAGER_LOG_FILE}
   if [ "$?" \> "2" ]
   then
    echo "备份档案表失败,请查看日志${DBMANAGER_LOG_FILE}"
    echo "备份档案表失败,请查看日志${DBMANAGER_LOG_FILE}">>${DBMANAGER_LOG_FILE}
    return 1
   fi
   
   echo "备份境外汇入主档表"
   echo "备份境外汇入主档表">>${DBMANAGER_LOG_FILE}
   db2 "EXPORT TO ${RIS_BATCH_DBBACKUP_PATH}/D_OVERSEAS_PAY${mydate}.IXF OF IXF SELECT * FROM RTCXU01.D_OVERSEAS_PAY " >>${DBMANAGER_LOG_FILE}
   if [ "$?" \> "2" ]
   then
    echo "备份境外汇入主档表失败,请查看日志${DBMANAGER_LOG_FILE}"
    echo "备份境外汇入主档表失败,请查看日志${DBMANAGER_LOG_FILE}">>${DBMANAGER_LOG_FILE}
    return 1
   fi
   
   echo "备份境外汇出主档表"
   echo "备份境外汇出主档表">>${DBMANAGER_LOG_FILE}
   db2 "EXPORT TO ${RIS_BATCH_DBBACKUP_PATH}/D_OVERSEAS_PAY_OUTGO${mydate}.IXF OF IXF SELECT * FROM RTCXU01.D_OVERSEAS_PAY_OUTGO " >>${DBMANAGER_LOG_FILE}
   if [ "$?" \> "2" ]
   then
    echo "备份境外汇出主档表失败,请查看日志${DBMANAGER_LOG_FILE}"
    echo "备份境外汇出主档表失败,请查看日志${DBMANAGER_LOG_FILE}">>${DBMANAGER_LOG_FILE}
    return 1
   fi
   
   chmod 777 ${RIS_BATCH_DBBACKUP_PATH}/*

   echo "压缩当日备份文件"
   echo "压缩当日备份文件">>${DBMANAGER_LOG_FILE}
   rm ${RIS_BATCH_DBBACKUP_PATH}/*${mydate}*.gz
   gzip ${RIS_BATCH_DBBACKUP_PATH}/*${mydate}.IXF
   
   echo "删除一个星期前的备份,如果不存在一个星期前的备份会有错误提示,不用理会"
   rm ${RIS_BATCH_DBBACKUP_PATH}/*${WeekAgo}*
   echo "备份完成"
   echo "备份完成">>${DBMANAGER_LOG_FILE}
   
   DisconnectDB
   return 0
  fi     
}
