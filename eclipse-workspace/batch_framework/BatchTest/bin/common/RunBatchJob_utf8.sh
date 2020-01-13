################################################################################
# $CIB_BATCH_BIN/RunBatchJob.sh
################################################################################

# run SetEnv.sh to set the environment variable

. $CIB_BATCH_BIN/SetEnv.sh

LOG_OUT=$CIB_BATCH_LOG/console.out
LOG_ERR=$CIB_BATCH_LOG/console.err

JOB_CONFIG="$1"

RUN_CLASS="com.boc.cib.batch.framework.RunBatchJob"
JOB_JAVA_PROP="-Dfile.encoding=UTF-8"
# run the JAVA class
java $JOB_JAVA_PROP -cp "$JAR_FILE:$LIB_FILE" -Xms32m -Xmx256m "$RUN_CLASS" $JOB_CONFIG $2 $3 $4 $5 1> $LOG_OUT 2> $LOG_ERR
