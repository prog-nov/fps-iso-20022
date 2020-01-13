###################################################################
# CibSetEmptyEnv.sh
###################################################################

#JAVA_VM should be set in AIX user profile
#JAVA_VM="/usr/java5_32/jre/bin"
#export $JAVA_VM
#$JAVA_VM/java -version

JAR_DIR="$CIB_BATCH_ROOT/runjar"
JAR_FILE="$JAR_DIR/CIB_BATCH.jar"
echo "JAR_FILE: $JAR_FILE"

LOG_DIR="$CIB_BATCH_LOG/log"
echo " in setenv : LOG_DIR: $LOG_DIR"

#
LIB_DIR="$CIB_BATCH_ROOT/lib"

LIB_FILE="$LIB_FILE:$LIB_DIR/log4j-1.2.13.jar"

LIB_FILE="$LIB_FILE:$CIB_ISB_JAR/asm.jar"
LIB_FILE="$LIB_FILE:$CIB_ISB_JAR/com.ibm.mq.jar"
LIB_FILE="$LIB_FILE:$ISB_LIB_JAR"
LIB_FILE="$LIB_FILE:$ISB_JAR"
LIB_FILE="$LIB_FILE:$CIB_ISB_JAR/j2ee.jar"
LIB_FILE="$LIB_FILE:$CIB_ISB_JAR/xstream-1.2.2.jar"

LIB_FILE="$LIB_FILE:$CIB_BATCH_ROOT/conf/cib_resources/mcf"

LIB_FILE="$LIB_FILE:$JAR_DIR"
echo "LIB_FILE: $LIB_FILE"