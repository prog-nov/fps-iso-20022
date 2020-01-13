#*************************************************#
# funcation: upload download file    					#
# create date: 2011/09/29													#
#*************************************************#

myhost=`hostname -s`
#ftpprop=$CIB_BATCH_ROOT/tscftp.properts
ftpInflog=$CIB_BATCH_LOG/ftpInfolog.log
ftpErrorlog=$CIB_BATCH_LOG/ftpErrorlog.log
ftpTmpCfg=$CIB_BATCH_LOG/tmpftp$$.cfg

. $CIB_BATCH_BIN/cibwritelog.sh

setftpenv()
{
	ftpprop=$1

	#check properties file exists or not 
	
	if [ ! -e $ftpprop ]
	then
		waitEcgLog 1 "ftp properties file $ftpprop is not exists..." 
		return 1
	fi

	
	ftpserver=`cat $ftpprop|grep ftpserver|cut -f 2 -d =`
	ftpusr=`cat $ftpprop|grep ftpusr|cut -f 2 -d =`
	remotpath=`cat $ftpprop|grep remotpath|cut -f 2 -d =`
	locpath=`cat $ftpprop|grep locpath|cut -f 2 -d =`
	filename=`cat $ftpprop|grep filename|cut -f 2 -d =`
	datesuffix=`cat $ftpprop|grep datesuffix|cut -f 2 -d =`
	
	# Check Ftp Setting
	if [ -z "$ftpserver" ]
	then
		waitEcgLog 1 "ftp properties file $ftpprop not ftpserver Setting..." 
		return 1
	fi

	if [ -z "$ftpusr" ]
	then
		waitEcgLog 1 "ftp properties file $ftpprop not ftpusr Setting..." 
		return 1
	fi

	if [ -z "$remotpath" ]
	then
		waitEcgLog 1 "ftp properties file $ftpprop not remotpath Setting..." 
		return 1
	fi

	if [ -z "$locpath" ]
	then
		waitEcgLog 1 "ftp properties file $ftpprop not locpath Setting..." 
		return 1
	fi
	
	if [ -z "$filename" ]
	then
		waitEcgLog 1 "ftp properties file $ftpprop not filename Setting..." 
		return 1
	fi
	
	#check file add suffix or not?
	if [ "$datesuffix" == "Y" ]
	then
		if [ -z "$filedate" ]
		then
			waitEcgLog 1 "ftp properties file $ftpprop set datesuffix item,but not input file date..." 
			return 1
		else
			adddatesuffix=$filedate
		fi
	fi
	return 0
}

#*************************************************#
# function: Get File From Ftp Server							#
# return: 0 Get File Succes												#
#         1 Get File Faild												#
#*************************************************#

uploadDataToFtp()
{
		objftpserver=$1
		curuploadfile=$2$adddatesuffix
		
		if [ ! -e $locpath/$2 ]
		then
			waitEcgLog 1 "file $locpath/$2 not exists" 
			return 1
		fi

		
		echo put $curuploadfile to Server	$ftpusr@$objftpserver
    echo cd $remotpath>$ftpTmpCfg
    echo lcd $locpath>>$ftpTmpCfg
    echo bin>>$ftpTmpCfg
    echo sput $2 $curuploadfile>>$ftpTmpCfg
    echo chmod 755 $curuploadfile>>$ftpTmpCfg
    echo bye>>$ftpTmpCfg
    
    sftp -B $ftpTmpCfg $ftpusr@$objftpserver 1>$ftpInflog 2>$ftpErrorlog
    if [ $? == 0 ]
    then
    	rm -f $ftpTmpCfg
    	waitEcgLog 0 "`cat $ftpInflog $ftpErrorlog`" 
    else
    	rm -f $ftpTmpCfg
   		cat $ftpErrorlog
   		waitEcgLog 1 "`cat $ftpErrorlog`" 
   		return 1
    fi
		return 0
}

#**************************************************#
#      main proceduce                              #
#      para1: 1- Get File From Ftp Server          #
#             2- put File To   Ftp Server          #
#      para2: ftp properties                   		 #
#      return: 0- Success                   		 	 #
#              1- fail                   		 	     #
#**************************************************#
mainproc()
{

	waitEcgLog 0 " Start Ftp Job,Ftp Para [$1,$2]..."
	
	if [ -z "$1" ]
	then
		waitEcgLog 1 "Execute Ftp Job,Should Input Ftp Properties"
		return 1
	fi 

	filedate=$2

	ftpsucc=Y
	
	setftpenv "$1"
	if [ $? != 0 ]
	then
		return 1
	fi
	
	
	# ****************************************************************#
	#                upload File to  Ftp Server                       #
	# ****************************************************************#
	waitEcgLog 0 " upload file $filename to Ftp server $ftpserver..."
	while true
	do
		ftpfilename=$filename
		ftpuploadserver=`echo $ftpserver|cut -f 1 -d ,`
		#loop ftpserver list if exists more than one ftp server
		
		while true
		do
			uploadfile=`echo $ftpfilename|cut -f 1 -d ,`
			uploadDataToFtp $ftpuploadserver $uploadfile
			if [ $? != 0 ]
			then
	  		waitEcgLog 0 " upload file $uploadfile to Sever $ftpuploadserver Fail..."
				ftpsucc=N
			fi
	
			ftpfilename=`echo $ftpfilename|cut -f 2- -d ,`
			if [ "$uploadfile" == "$ftpfilename" ]
			then
				break
			fi
		done
		
		ftpserver=`echo $ftpserver|cut -f 2- -d ,`
		if [ "$ftpuploadserver" == "$ftpserver" ]
		then
			break
		fi		
	done		

	if [ $ftpsucc == Y ]
	then
		waitEcgLog 0 " End Ftp Job,Job Success..."
		return 0
	else
		waitEcgLog 0 " End Ftp Job,Job fail..."
		return 1
	fi	
}

mainproc $1 $2
