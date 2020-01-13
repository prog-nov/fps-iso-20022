#*************************************************#
# funcation: upload or download file    					#
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
	cmpfile=`cat $ftpprop|grep cmpfile|cut -f 2 -d =`
	tmppath=`cat $ftpprop|grep tmppath|cut -f 2 -d =`
	
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
	
	if [ "$cmpfile" == "Y" ]
	then
		if [ -z "$tmppath" ]
		then
			waitEcgLog 1 "ftp properties file $ftpprop set cmpfile is Y,but $tmppath not setting..." 
			return 1
		fi
		
		if [ ! -d $tmppath ]
		then
			waitEcgLog 1 "temp path $tmppath not exists..." 
			return 1
		fi
		
		tmpftplocpath=$tmppath
	else
		tmpftplocpath=$locpath
	fi
	
	return 0
}

#*************************************************#
# function: Get File From Ftp Server							#
# return: 0 Get File Succes												#
#         1 Get File Faild												#
#*************************************************#

downloadDataFromFtp()
{
		#delete from locate if object file exists
		downloadfile=$1$adddatesuffix
		
		if [ -e $tmpftplocpath/$1 ]
		then
			rm -f $tmpftplocpath/$1
		fi
		
    echo cd $remotpath>$ftpTmpCfg
    echo lcd $tmpftplocpath>>$ftpTmpCfg
    echo bin>>$ftpTmpCfg
    echo sget $downloadfile $1>>$ftpTmpCfg
    echo bye>>$ftpTmpCfg

    sftp -B $ftpTmpCfg $ftpusr@$ftpserver 1>$ftpInflog 2>$ftpErrorlog
    if [ $? == 0 ]
    then
    	rm -f $ftpTmpCfg
    	waitEcgLog 0 "`cat $ftpInflog $ftpErrorlog`" 
    	if [ ! -e $tmpftplocpath/$1 ]
    	then
    		waitEcgLog 1 "file $downloadfile not exists at ftp server..." 
    		return 1
    	fi
    else
    	 rm -f $ftpTmpCfg
    	if [ -s $ftpErrorlog ]
    	then 
    		cat $ftpErrorlog
    		waitEcgLog 1 "`cat $ftpErrorlog`" 
    		return 1
    	fi
    fi
		return 0
}

#*************************************************#
# function: cmp two  file 												#
# para: 1- compare file 												  #
# para: 2- object compare file 										#
# return: 0 two files are same										#
#         1 two files are diffrent								#
#*************************************************#

cmpfilewithftpserver()
{
		if [ ! -e $1 ]
		then
			waitEcgLog 1 "File $1 is not exists..."
			return 2
		fi
		
		if [ ! -e $2 ]
		then
			waitEcgLog 0 "File $2 Is Not Exists, File $1 Is New File..."
			return 1
		fi
		
		echo cmp -s $1 $2
		cmp -s $1 $2
		
		if [ $? == 0 ]
		then
			waitEcgLog 0 "There Are No Diffrents With Two File $1 And $2..."
			return 0
		else
			return 1
		fi
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
	#                download File From Ftp Server                    #
	# ****************************************************************#
	waitEcgLog 0 " Download file $filename from Ftp server $ftpserver..."
	
	while true
	do
		downfile=`echo $filename|cut -f 1 -d ,`
		downloadDataFromFtp $downfile
		if [ $? != 0 ]
		then
  		waitEcgLog 0 " End Ftp Job,Job Fail..."
			ftpsucc=N
		else
			#need compare local file with ftp server file
			if [ $cmpfile == Y ]
			then
				cmpfilewithftpserver $tmppath/$downfile $locpath/$downfile
			  if [ $? == 0 ]
			  then
			  	ftpsucc=N
			  else
			  	cp -f $tmppath/$downfile $locpath/$downfile
			  fi
			fi
		fi
		filename=`echo $filename|cut -f 2- -d ,`
		if [ "$downfile" == "$filename" ]
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
