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

#ģ�鹦�ܣ��������ݿ�
ConnectDB()
{
  db2 connect to $_DBNAME user ${_DBUSER} using ${_DBPWD}
  if [ $? != 0 ]
  then
    echo "�������ݿ�ʧ�ܣ��������ݿ����Ա��ϵ"
    echo "�������ݿ�ʧ�ܣ��������ݿ����Ա��ϵ">>${DBMANAGER_LOG_FILE}
    return 1    
  else
    echo "�������ݿ�ɹ�"
    echo "�������ݿ�ɹ�">>${DBMANAGER_LOG_FILE}
    return 0
  fi
}

#�Ͽ����ݿ�����
DisconnectDB()
{
  db2 disconnect $_DBNAME
  echo "�Ͽ����ݿ�����$_DBNAME"
  echo "�Ͽ����ݿ�����$_DBNAME">>${DBMANAGER_LOG_FILE}
}

#������ݿ��״̬
CheckDbStatus()
{
	ConnectDB
	if [ $? = "0" ]
	then
  	echo "������ݿ��״̬,��������б�״̬�쳣��ʾ,����ϵά����Ա,�������������ʾ"
  	for tbname in `db2 -X "select name as tablename from sysibm.systables where creator = 'RTCXU01' and TYPE = 'T' and STATUS <> 'N' ORDER BY NAME"` ;do echo "��״̬"${tbname}"������" && echo "��״̬"${tbname}"������">>${DBMANAGER_LOG_FILE};done
		DisconnectDB
	fi
}

#��������
ReorgDB()
{
	ConnectDB
	echo $?
	if [ $? = "0" ]
	then
   	echo "�����������ݱ�����"
   	echo "�����������ݱ�����">>${DBMANAGER_LOG_FILE}
   	for tbname in `db2 -x "select name from sysibm.systables where creator = 'RTCXU01' and type = 'T' ORDER BY NAME "`;do echo "${mydate}�������ݱ�${tbname}����"  && db2 "REORG  TABLE RTCXU01.${tbname} ALLOW READ ACCESS " >> ${DBMANAGER_LOG_FILE};done
   	echo "��������������Ϣ"
   	echo "��������������Ϣ">>${DBMANAGER_LOG_FILE}
   	for tbname in `db2 -x "select name from sysibm.systables where creator = 'RTCXU01' and type = 'T' ORDER BY NAME "`;do echo "${mydate}�������ݱ�${tbname}����"  && db2 "REORG  INDEXES ALL FOR TABLE RTCXU01.${tbname} ALLOW READ ACCESS " >> ${DBMANAGER_LOG_FILE};done
   	echo "�������б��ͳ����Ϣ"
   	echo "�������б��ͳ����Ϣ">>${DBMANAGER_LOG_FILE}
   	for tbname in `db2 -x "select name from sysibm.systables where creator = 'RTCXU01' and type = 'T' ORDER BY NAME "`;do echo "${mydate}�������ݱ�${tbname}����"  && db2 " RUNSTATS ON TABLE RTCXU01.${tbname} ON KEY COLUMNS  WITH DISTRIBUTION ON ALL COLUMNS  AND DETAILED INDEX ALL  ALLOW READ ACCESS " >> ${DBMANAGER_LOG_FILE};done 
   	DisconnectDB
	return 0
  fi
}

#��ȡ��ǰʱ��
GetDBDate()
{
OUT_FILE="${RIS_BATCH_DBBACKUP_PATH}/DATE${mydate}.dat"
echo ${OUT_FILE}
echo "��ȡϵͳ��������"
echo "��ȡϵͳ��������">>${DBMANAGER_LOG_FILE}
ConnectDB
db2 "export to $OUT_FILE of del  MODIFIED BY CHARDEL#  values(Replace(CHAR(Current Date - 7 days,ISO), '-','') || '#' || Replace(CHAR(Current Date,ISO), '-','') || '#' || CHAR(CURRENT DATE - 1 YEAR,ISO) || '#' || (SELECT COALESCE(MAX(RIGHT(OREF_NO,5)),'00000')  FROM RTCXU01.D_RTS_HDUI WHERE SUBSTR(OREF_NO,6,6) = RIGHT(REPLACE(CAST(CURRENT DATE AS CHAR(10)),'-',''),6)))">> ${DBMANAGER_LOG_FILE}
DisconnectDB

if [ "$?" \> "2" ]
then
  echo "��ȡϵͳʱ��ʧ��"
  echo "��ȡϵͳʱ��ʧ��">>${DBMANAGER_LOG_FILE}
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
echo "һ������ǰ���ڣ�"${WeekAgo}
echo "�������ڣ�"${CurrDay}
echo "һ��ǰ���ڣ�"${YearAgo}
echo "��ǰ����ݣ�"${CurrYear}
echo "ȥ�����:"${AgoYear}
}

#�������ݿ�
BackupDb()
{
	GetDBDate
	ConnectDB
	if [ $? = "0" ]
	then
   echo "��������������"
   echo "��������������">>${DBMANAGER_LOG_FILE}
   db2 "EXPORT TO ${RIS_BATCH_DBBACKUP_PATH}/M_OVERSEAS_RECV${mydate}.IXF OF IXF SELECT * FROM RTCXU01.M_OVERSEAS_RECV " >>${DBMANAGER_LOG_FILE}
   if [ "$?" \> "2" ]
   then
    echo "��������������ʧ��,��鿴��־${DBMANAGER_LOG_FILE}"
    echo "��������������ʧ��,��鿴��־${DBMANAGER_LOG_FILE}">>${DBMANAGER_LOG_FILE}
    return 1
   fi  

   echo "��������������"
   echo "��������������">>${DBMANAGER_LOG_FILE}
   db2 "EXPORT TO ${RIS_BATCH_DBBACKUP_PATH}/M_OVERSEAS_SEND${mydate}.IXF OF IXF SELECT * FROM RTCXU01.M_OVERSEAS_SEND " >>${DBMANAGER_LOG_FILE}
   if [ "$?" \> "2" ]
   then
    echo "��������������ʧ��,��鿴��־${DBMANAGER_LOG_FILE}"
    echo "��������������ʧ��,��鿴��־${DBMANAGER_LOG_FILE}">>${DBMANAGER_LOG_FILE}
    return 1
   fi  

   echo "�����ڲ�����������"
   echo "�����ڲ�����������">>${DBMANAGER_LOG_FILE}
   db2 "EXPORT TO ${RIS_BATCH_DBBACKUP_PATH}/M_OVERSEAS_WITHIN${mydate}.IXF OF IXF SELECT * FROM RTCXU01.M_OVERSEAS_WITHIN " >>${DBMANAGER_LOG_FILE}
   if [ "$?" \> "2" ]
   then
    echo "�����ڲ�����������ʧ��,��鿴��־${DBMANAGER_LOG_FILE}"
    echo "�����ڲ�����������ʧ��,��鿴��־${DBMANAGER_LOG_FILE}">>${DBMANAGER_LOG_FILE}
    return 1
   fi  
   
   echo "���ݵ���������"
   echo "���ݵ���������">>${DBMANAGER_LOG_FILE}
   db2 "EXPORT TO ${RIS_BATCH_DBBACKUP_PATH}/T_OVERSEAS_ARCHIVES${mydate}.IXF OF IXF SELECT * FROM RTCXU01.T_OVERSEAS_ARCHIVES " >>${DBMANAGER_LOG_FILE}
   if [ "$?" \> "2" ]
   then
    echo "���ݵ�����ʧ��,��鿴��־${DBMANAGER_LOG_FILE}"
    echo "���ݵ�����ʧ��,��鿴��־${DBMANAGER_LOG_FILE}">>${DBMANAGER_LOG_FILE}
    return 1
   fi
   
   echo "���ݾ������������"
   echo "���ݾ������������">>${DBMANAGER_LOG_FILE}
   db2 "EXPORT TO ${RIS_BATCH_DBBACKUP_PATH}/D_OVERSEAS_PAY${mydate}.IXF OF IXF SELECT * FROM RTCXU01.D_OVERSEAS_PAY " >>${DBMANAGER_LOG_FILE}
   if [ "$?" \> "2" ]
   then
    echo "���ݾ������������ʧ��,��鿴��־${DBMANAGER_LOG_FILE}"
    echo "���ݾ������������ʧ��,��鿴��־${DBMANAGER_LOG_FILE}">>${DBMANAGER_LOG_FILE}
    return 1
   fi
   
   echo "���ݾ�����������"
   echo "���ݾ�����������">>${DBMANAGER_LOG_FILE}
   db2 "EXPORT TO ${RIS_BATCH_DBBACKUP_PATH}/D_OVERSEAS_PAY_OUTGO${mydate}.IXF OF IXF SELECT * FROM RTCXU01.D_OVERSEAS_PAY_OUTGO " >>${DBMANAGER_LOG_FILE}
   if [ "$?" \> "2" ]
   then
    echo "���ݾ�����������ʧ��,��鿴��־${DBMANAGER_LOG_FILE}"
    echo "���ݾ�����������ʧ��,��鿴��־${DBMANAGER_LOG_FILE}">>${DBMANAGER_LOG_FILE}
    return 1
   fi
   
   chmod 777 ${RIS_BATCH_DBBACKUP_PATH}/*

   echo "ѹ�����ձ����ļ�"
   echo "ѹ�����ձ����ļ�">>${DBMANAGER_LOG_FILE}
   rm ${RIS_BATCH_DBBACKUP_PATH}/*${mydate}*.gz
   gzip ${RIS_BATCH_DBBACKUP_PATH}/*${mydate}.IXF
   
   echo "ɾ��һ������ǰ�ı���,���������һ������ǰ�ı��ݻ��д�����ʾ,�������"
   rm ${RIS_BATCH_DBBACKUP_PATH}/*${WeekAgo}*
   echo "�������"
   echo "�������">>${DBMANAGER_LOG_FILE}
   
   DisconnectDB
   return 0
  fi     
}
