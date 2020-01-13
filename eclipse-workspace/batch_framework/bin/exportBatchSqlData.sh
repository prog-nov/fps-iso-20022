. $CIB_BATCH_BIN/cibwritelog.sh

exportSqldata()
{	
		tablename=$1
		acdate=$2
		addflag=$3
		sqlfile=$4
		if [ $addflag == Y ]
		then
			suffix=$acdate
		fi
		
		if [ ! -e $sqlfile ]
		then
			waitEcgLog 2 "file $sqlfile not exists" 
			return 1
		fi
		db2 export to $backuppath/$acdate/$tablename$suffix.ixf of ixf lobs to $backuppath/$acdate lobfile $tablename$suffix  "`cat $sqlfile`">$CIB_BATCH_LOG/export.log
		if [ $? != 0 ]
		then
			waitEcgLog 2 "export table $sqlfile fail,`cat $CIB_BATCH_LOG/export.log`"
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
			waitEcgLog 0 "export table date from  $tablename Record count: $exportcnt"
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
 		configFilename=$3
 		failFile=$4
		
	
		db2 connect to $dbname
		if [ $? != 0 ]
		then
			waitEcgLog 1 "connect cibdb fail"
			return 1
		fi
		
	 
		if [ ! -e $configFilename ]
		then
			waitEcgLog 1 "invaild configFile"
			db2 disconnect $dbname
			return 1
		else
			rm -f ${failFile}		
			while read filename 
			do
				exportSqldata $filename $acdate $addflag $CIB_BATCH_DBBACKUP_PATH/PROPERTY/${filename}.properties
				if [ $? != 0 ]
				then
				waitEcgLog 2 "export $filename fail"	
				echo $filename >> ${failFile}
					if [ $? != 0 ]
					then
						waitEcgLog 1 "write export failfile fail"	
						db2 disconnect $dbname
						return 1
					fi
				fi
			done < $configFilename
			
		fi
		
		db2 disconnect $dbname
		return 0
}	

mainproc $1 $2 $3 $4