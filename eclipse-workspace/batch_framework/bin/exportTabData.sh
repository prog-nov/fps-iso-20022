. $CIB_BATCH_BIN/cibwritelog.sh

exportTabdata()
{	
		tabname=$1
		acdate=$2
		addflag=$3
		
		if [ $addflag == Y ]
		then
			suffix=$acdate
		fi
		
		db2 export to $backuppath/$acdate/$tabname$suffix.ixf of ixf lobs to $backuppath/$acdate lobfile $tabname$suffix modified by lobsinfile "select * from $schema.$tabname">$CIB_BATCH_LOG/export.log
		if [ $? != 0 ]
		then
			waitEcgLog 1 "export table $tabname fail,`cat $CIB_BATCH_LOG/export.log`" 
			rm -f $CIB_BATCH_LOG/export.log
			return 1
		fi
		
		exportcnt=`cat $CIB_BATCH_LOG/export.log|grep "Number of rows exported:"|cut -f 2 -d :`
		if [ -z $exportcnt ]
		then
			rm -f $CIB_BATCH_LOG/export.log
		  return 1
		else
			waitEcgLog 0 "`cat $CIB_BATCH_LOG/export.log`"
			waitEcgLog 0 "export table date from  $tabname Record count: $exportcnt"
			rm -f $CIB_BATCH_LOG/export.log
			
			compress -f $backuppath/$acdate/$tabname$suffix.ixf
			if [ $? != 0 ]
			then
				waitEcgLog 1 "compress file $backuppath/$acdate/$tabname$suffix.ixf fail"
				return 1
			fi			
		fi
		return 0
		
}

mainproc()
{

		dbname=`cat $CIB_BATCH_CONFINFO/cibdb.properties|grep "url"|cut -f 4 -d /`
		schema=`cat $CIB_BATCH_CONFINFO/cibdb.properties|grep "defaultSchema"|cut -f 2 -d =`
		backuppath=$CIB_BATCH_DBBACKUP_PATH
		
	 	acdate=$1
		addflag=$2
		propfile=$3
			
		db2 connect to $dbname
		if [ $? != 0 ]
		then
			waitEcgLog 1 "connect db $dbname fail"
			return 1
		fi
		
		while read tabname
		do
			exportTabdata $tabname $acdate $addflag
			if [ $? != 0 ]
			then
				db2 disconnect $dbname
				return 1
			fi
		done<$propfile

		db2 disconnect $dbname
		return 0
}	

mainproc $1 $2 $3