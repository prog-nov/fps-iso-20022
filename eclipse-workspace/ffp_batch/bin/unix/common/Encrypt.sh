################################################################################
# Encrypt.sh
################################################################################

JAR_FILE="./batch_framework-2.0.jar"

RUN_CLASS="com.forms.framework.util.EncryptUtil"
java -cp "$JAR_FILE" "$RUN_CLASS" $1 $2
