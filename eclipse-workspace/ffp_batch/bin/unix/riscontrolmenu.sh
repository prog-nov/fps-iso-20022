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
	echo "������JOB����ִ������(YYYY-MM-DD),������س�,����������R or r"
	read rerundate
	if [ "R" = "$rerundate" -o "r" = "$rerundate" ]
	then
		return 0
	fi
	
	if [ -z "$rerundate" ]
	then
		echo "����ִ�� $jobname,���Ժ�"
		sh $jobname
		result=$?
	else
		echo "����ִ�� $jobname -D $rerundate,���Ժ�"
		sh $jobname -D $rerundate
		result=$?
	fi
	if [ $result != "0" ]
	then
		echo "ִ��ʧ��,������־�ļ�"
		return 1
	fi
}

while :
do
  #clear the screen
  tput clear
  #cat <<mymenu
  echo "  ����ѯ������ϴ��������Ʋ˵�(deal data batch control memu of ris)         "
  echo "______________________________________________________________________________"
  echo "system: ris                Host: $myhost            Date: $mydate             "
  echo "______________________________________________________________________________"
  echo "      1: ���뱨����Ϣ����¼���м��       2: ���뱨�Ĵ��������¼���м��     "
  echo "______________________________________________________________________________"
  echo "      3: ���������Ϣ¼���м��           4: ����֧������¼����������         "
  echo "______________________________________________________________________________"
  echo "      5: ��ѯ������Ϣ¼����������         6: ���������Ϣ¼����������         "
  echo "______________________________________________________________________________"
  echo "      7: ֧�����Ľ�����ʷ����¼��         8: ���»���֧�������˱��ҷ����     "
  echo "______________________________________________________________________________"
  echo "      9: �Զ���������                     A: �������֧��������Ϣ             "
  echo "______________________________________________________________________________"
  echo "      B: ������֧��������Ϣ             C: �����ѯ������Ϣ                 "
  echo "______________________________________________________________________________"
  echo "      D: �����м��                       E: ��������                         "
  echo "______________________________________________________________________________"
  echo "      F: ��������״̬                   G: ���ݵ������ݿ�����               "
  echo "______________________________________________________________________________"
  echo "      Q: �˳�                                                                 "
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
    echo "�밴�س������� "
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
    echo "�밴�س������� "
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
    echo "�밴�س������� "
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
    echo "�밴�س������� "
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
    echo "�밴�س������� "
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
    echo "�밴�س������� "
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
    echo "�밴�س������� "
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
    echo "�밴�س������� "
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
    echo "�밴�س������� "
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
    echo "�밴�س������� "
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
    echo "�밴�س������� "
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
    echo "�밴�س������� "
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
    echo "�밴�س������� "
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
    echo "�밴�س������� "
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
    echo "�밴�س������� "
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
    echo "�밴�س������� "
    read kk  
    continue
  fi
  ;;
  Q|q)
  echo `date +'%Y-%-m-%d %H:%M:%S'`
  echo  "�˳����ܲ˵�"
  break
  ;;
  *) 
  echo "007unknown user response"
  esac
  echo "�밴�س������� "
  read kk
done  
 