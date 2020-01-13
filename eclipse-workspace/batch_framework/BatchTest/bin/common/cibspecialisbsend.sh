################################################################################
# cibspcsend.sh
################################################################################

. $CIB_BATCH_BIN/cibwritelog.sh

#************************************************#
#    Check Parms Is Ok                           #
#************************************************#
if [ -z "$1" ]
then
	waitEcgLog 1  "pls check input params,must be input properties file"
	return 1
fi

if [ -z "$2" ]
then
	waitEcgLog 1  "pls check input params,must be input account date"
	return 1
fi

JOB_CONFIG=$1

if [ ! -e $CIB_BATCH_ROOT/$JOB_CONFIG ]
then
	waitEcgLog 1  "properties file $CIB_BATCH_ROOT/$JOB_CONFIG not exists!!"
	return 1
fi

#*************************************************#
#    Gen Email Empty File                         #
#*************************************************#
curdate=$2
fileacdate="`echo $2|cut -c 1-4`/`echo $2|cut -c 6-7`/`echo $2|cut -c 9-10`"

$CIB_BATCH_BIN/cibgenemptyiff.sh $curdate $JOB_CONFIG
if [ $? != 0 ]
then
	waitEcgLog 1  "Gen Token Empty File Fail"
	return 1
fi

#run $CIB_BATCH_BIN/CibRunEmptyBatchJob.sh
$CIB_BATCH_BIN/CibRunEmptyBatchJob.sh $JOB_CONFIG $2
                                                                        