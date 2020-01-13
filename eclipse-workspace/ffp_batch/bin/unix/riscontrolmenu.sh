#list menu
mydate=`date +%Y%m%d`
mytime=`date +'%Y-%m-%d %H:%M:%S'`
myhost=`hostname -s`
_DBNAME="rtcxdb"
_DBUSER="rtcxdbm"
_DBPWD="rtcxrt"
_LOG_FILE=$RIS_BATCH_LOG/RisCtrlMenu.log
. $RIS_BATCH_BIN/risdbmanager.sh

exejob()
{
	jobname=$1
	echo "请输入JOB重新执行日期(YYYY-MM-DD),当天请回车,返回请输入R or r"
	read rerundate
	if [ "R" = "$rerundate" -o "r" = "$rerundate" ]
	then
		return 0
	fi
	
	if [ -z "$rerundate" ]
	then
		echo "正在执行 $jobname,请稍后"
		sh $jobname
		result=$?
	else
		echo "正在执行 $jobname -D $rerundate,请稍后"
		sh $jobname -D $rerundate
		result=$?
	fi
	if [ $result != "0" ]
	then
		echo "执行失败,请检查日志文件"
		return 1
	fi
}

while :
do
  #clear the screen
  tput clear
  #cat <<mymenu
  echo "  汇款查询数据清洗批处理控制菜单(deal data batch control memu of ris)         "
  echo "______________________________________________________________________________"
  echo "system: ris                Host: $myhost            Date: $mydate             "
  echo "______________________________________________________________________________"
  echo "      1: 汇入报文信息数据录入中间表       2: 汇入报文贷方处理表录入中间表     "
  echo "______________________________________________________________________________"
  echo "      3: 汇出报文信息录入中间表           4: 汇入支付报文录入数据主表         "
  echo "______________________________________________________________________________"
  echo "      5: 查询报文信息录入数据主表         6: 汇出报文信息录入数据主表         "
  echo "______________________________________________________________________________"
  echo "      7: 支付报文交易历史数据录入         8: 更新汇入支付报文退报我方编号     "
  echo "______________________________________________________________________________"
  echo "      9: 自动勾连建档                     A: 清理汇入支付报文信息             "
  echo "______________________________________________________________________________"
  echo "      B: 清理汇出支付报文信息             C: 清理查询报文信息                 "
  echo "______________________________________________________________________________"
  echo "      D: 清理中间表                       E: 重整数据                         "
  echo "______________________________________________________________________________"
  echo "      F: 检查基础表状态                   G: 备份当天数据库主档               "
  echo "______________________________________________________________________________"
  echo "      Q: 退出                                                                 "
  echo "______________________________________________________________________________"
  #mymenu
  echo "Please Choice [1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,Q] >"

  read choice
  case $choice in 
  1)
  echo `date +'%Y-%-m-%d %H:%M:%S'`
  echo
  exejob risdq1.sh
  if [ $? = "0" ]
  then
    echo "请按回车键继续 "
    read kk  
    continue
  fi
  ;;
  2)
  echo `date +'%Y-%-m-%d %H:%M:%S'`
  echo
  exejob risp21.sh
  if [ $? = "0" ]
  then
    echo "请按回车键继续 "
    read kk  
    continue
  fi
  ;;
  3) 
  echo `date +'%Y-%-m-%d %H:%M:%S'`
  echo
  exejob risp31.sh
  if [ $? = "0" ]
  then
    echo "请按回车键继续 "
    read kk  
    continue
  fi
  ;;
  4)
  echo `date +'%Y-%-m-%d %H:%M:%S'`
  echo
  exejob risdealpay.sh
  if [ $? = "0" ]
  then
    echo "请按回车键继续 "
    read kk  
    continue
  fi
  ;;
  5)
  echo `date +'%Y-%-m-%d %H:%M:%S'`
  echo
  exejob risdealmoverseas.sh
  if [ $? = "0" ]
  then
    echo "请按回车键继续 "
    read kk  
    continue
  fi
  ;;
  6)
  echo `date +'%Y-%-m-%d %H:%M:%S'`
  echo
  exejob risdealpayout.sh
  if [ $? = "0" ]
  then
    echo "请按回车键继续 "
    read kk  
    continue
  fi
  ;;
  7)
  echo `date +'%Y-%-m-%d %H:%M:%S'`
  echo
  exejob risec1.sh
  if [ $? = "0" ]
  then
    echo "请按回车键继续 "
    read kk  
    continue
  fi
  ;;
  8)
  echo `date +'%Y-%-m-%d %H:%M:%S'`
  echo
  exejob risdealbackourno.sh
  if [ $? = "0" ]
  then
    echo "请按回车键继续 "
    read kk  
    continue
  fi
  ;;
  9)
  echo `date +'%Y-%-m-%d %H:%M:%S'`
  echo
  exejob risdealarchive.sh
  if [ $? = "0" ]
  then
    echo "请按回车键继续 "
    read kk  
    continue
  fi
  ;;
  A|a)
  echo `date +'%Y-%-m-%d %H:%M:%S'`
  echo
  exejob rishuspayin.sh
  if [ $? = "0" ]
  then
    echo "请按回车键继续 "
    read kk  
    continue
  fi
  ;;  
  B|b)
  echo `date +'%Y-%-m-%d %H:%M:%S'`
  echo
  exejob rishuspayout.sh
  if [ $? = "0" ]
  then
    echo "请按回车键继续 "
    read kk  
    continue
  fi
  ;;  
  C|c)
  echo `date +'%Y-%-m-%d %H:%M:%S'`
  echo
  exejob rishusmoverseas.sh
  if [ $? = "0" ]
  then
    echo "请按回车键继续 "
    read kk  
    continue
  fi
  ;;   
  D|d) 
  echo `date +'%Y-%-m-%d %H:%M:%S'`
  echo
  exejob rishustemptable.sh
  if [ $? = "0" ]
  then
    echo "请按回车键继续 "
    read kk  
    continue
  fi
  ;;
  E|e) 
  echo `date +'%Y-%-m-%d %H:%M:%S'`
  echo
  ReorgDB
  if [ $? = "0" ]
  then
    echo "请按回车键继续 "
    read kk  
    continue
  fi
  ;;
  F|f) 
  echo `date +'%Y-%-m-%d %H:%M:%S'`
  echo
  CheckDbStatus
  if [ $? = "0" ]
  then
    echo "请按回车键继续 "
    read kk  
    continue
  fi
  ;;
  G|g) 
  echo `date +'%Y-%-m-%d %H:%M:%S'`
  echo
  BackupDb
  if [ $? = "0" ]
  then
    echo "请按回车键继续 "
    read kk  
    continue
  fi
  ;;
  Q|q)
  echo `date +'%Y-%-m-%d %H:%M:%S'`
  echo  "退出功能菜单"
  break
  ;;
  *) 
  echo "007unknown user response"
  esac
  echo "请按回车键继续 "
  read kk
done  
 