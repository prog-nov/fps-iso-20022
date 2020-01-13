ECHO "STOP_BATCH_MSG_LISTENER.BAT"

CD C:\ffp\BATCH\bin\windows\

SET CONFIG="job/participant/receivemsg/stop-receivemsg.xml"

call common\RunBatchJob_utf8.BAT %CONFIG% %1 %2 %3 %4 %5


