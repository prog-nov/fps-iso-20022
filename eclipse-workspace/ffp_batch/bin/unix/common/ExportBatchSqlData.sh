. $RIS_BATCH_BIN/common/Writelog.sh

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
		db2 export to $backuppath/$acdate/$tablename$suffix.ixf of ixf lobs to $backuppath/$acdate lobfile $tablename$suffix  "`cat $sqlfile`">$RIS_BATCH_LOG/export.log
		if [ $? != 0 ]
		then
			waitEcgLog 2 "export table $sqlfile fail,`cat $RIS_BATCH_LOG/export.log`"
			rm -f $RIS_BATCH_LOG/export.log
			return 1
		fi
		
		exportcnt=`cat $RIS_BATCH_LOG/export.log|grep "Number of rows exported:"|cut -f 2 -d :`
		if [ -z $exportcnt ]
		then
			rm -f $RIS_BATCH_LOG/export.log
		  return 1
		else
			waitEcgLog 0 "`cat $RIS_BATCH_LOG/export.log`"
			waitEcgLog 0 "export table date from  $tablename Record count: $exportcnt"
			rm -f $RIS_BATCH_LOG/export.log
		fi
		return 0
}

mainproc()
{
		dbname=`cat $RIS_BATCH_CONFINFO/db.properties|grep "url"|cut -f 4 -d /`
		schema=`cat $RIS_BATCH_CONFINFO/db.properties|grep "defaultSchema"|cut -f 2 -d =`
		backuppath=$RIS_BATCH_DBBACKUP_PATH

		acdate=$1
		addflag=$2
 		configFilename=$3
 		failFile=$4
		
	
		db2 connect to $dbname
		if [ $? != 0 ]
		then
			waitEcgLog 1 "connect risdb fail"
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
				exportSqldata $filename $acdate $addflag $RIS_BATCH_DBBACKUP_PATH/PROPERTY/${filename}.properties
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