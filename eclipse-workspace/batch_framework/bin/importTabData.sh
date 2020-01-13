. $CIB_BATCH_BIN/cibwritelog.sh
result=0
importTabdata()
{	
		tabname=$1
		acdate=$2
		addflag=$3
		
		if [ $addflag == Y ]
		then
			suffix=$acdate
		fi
		
		if [ -e $backuppath/$acdate/$tabname$suffix.ixf.Z ]	
		then	
			compress -d $backuppath/$acdate/$tabname$suffix.ixf.Z
			if [ $? != 0 ]
				then
					waitEcgLog 1 "decompress file $backuppath/$acdate/$tabname$suffix.ixf.Z fail"
					return 1
			fi
		fi
		
		db2 import from $backuppath/$acdate/$tabname$suffix.ixf of ixf lobs from $backuppath/$acdate modified by lobsinfile replace into $schema.$tabname>$CIB_BATCH_LOG/export.log
		if [ $? != 0 ]
		then
			waitEcgLog 1 "import table $tabname fail,`head -n 200 $CIB_BATCH_LOG/export.log`" 
			rm -f $CIB_BATCH_LOG/export.log
			compress -f $backuppath/$acdate/$tabname$suffix.ixf	
			return 1		
		fi
		
		compress -f $backuppath/$acdate/$tabname$suffix.ixf
		if [ $? != 0 ]
			then
				waitEcgLog 1 "compress file $backuppath/$acdate/$tabname$suffix.ixf fail"
				return 1
		fi
		
		exportcnt=`cat $CIB_BATCH_LOG/export.log|grep "Number of rows committed ="|cut -f 2 -d =`
		if [ -z $exportcnt ]
		then
			rm -f $CIB_BATCH_LOG/export.log
		  return 1
		else
			waitEcgLog 0 "`cat $CIB_BATCH_LOG/export.log`"			
			rm -f $CIB_BATCH_LOG/export.log			
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
			importTabdata $tabname $acdate $addflag
			if [ $? != 0 ]
			then
				result=1				
			fi
		done<$propfile

		db2 disconnect $dbname
		if [ $result == 1 ]
		then
			return 1
		fi
		return 0
}	

mainproc $1 $2 $3