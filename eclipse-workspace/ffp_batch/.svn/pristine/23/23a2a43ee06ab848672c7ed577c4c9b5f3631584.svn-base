
rem %BATCH_PRO_ROOT%\bin\windows\RunBatchJob_utf8.bat

rem run SetEnv.sh to set the environment variable

rem CD %BATCH_BIN%

CD C:\ffp\BATCH\bin\windows

CALL common\SetEnv.BAT

SET LOG_OUT=%LOG_DIR%\console.out
SET LOG_ERR=%LOG_DIR%\console.err

SET JOB_CONFIG="%1"

SET RUN_CLASS=com.forms.batch.MainApp
SET JOB_JAVA_PROP=-Dfile.encoding=UTF-8

java %JOB_JAVA_PROP% -cp %JAR_FILE%;%LIB_FILE% -Xms32m -Xmx256m %RUN_CLASS% %JOB_CONFIG% %2 %3 %4 %5
