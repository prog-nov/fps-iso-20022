. $CIB_BATCH_BIN/cibwritelog.sh

mainproc()
{

		filePath=$1
		if [ ! -r $filePath ]
		then
			waitEcgLog 2 "$filePath not exists"
			return 0
		fi
		for filename in `find $filePath -type f -name "*\.ixf"`
		do
			if [ -e $filename ]
			then
				compress -f $filename
				if [ $? != 0 ]
				then
					waitEcgLog 2 "compress $filename fail"
				fi
			fi
		done		
}	

mainproc $1 