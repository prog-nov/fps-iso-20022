. $CIB_BATCH_BIN/cibwritelog.sh

exportSqldata()
{	
		tabname=$1
		acdate=$2
		addflag=$3
		sqlfile=$4
		
		if [ $addflag == Y ]
		then
			suffix=$acdate
		fi
		
		if [ ! -e $sqlfile ]
		then
			waitEcgLog 1 "file $sqlfile not exists" 
			return 1
		fi
		
		db2 export to $backuppath/$acdate/$tabname$suffix.ixf of ixf lobs to $backuppath/$acdate lobfile $tabname$suffix modified by lobsinfile "`cat $sqlfile`">$CIB_BATCH_LOG/export.log
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
 		tabname=$3
		exportsql=$4
	
		db2 connect to $dbname
		if [ $? != 0 ]
		then
			waitEcgLog 1 "connect cibdb fail"
			return 1
		fi
		
	 
		if [ -z $exportsql ]
		then
			waitEcgLog 1 "invaild Sql file"
			db2 disconnect $dbname
			return 1
		else
			exportSqldata $tabname $acdate $addflag $exportsql
			if [ $? != 0 ]
			then
				waitEcgLog 1 "exportSqldata $tabname fail"
				db2 disconnect $dbname
				return 1
			fi
		fi
		
		db2 disconnect $dbname
		return 0
}	

mainproc $1 $2 $3 $4