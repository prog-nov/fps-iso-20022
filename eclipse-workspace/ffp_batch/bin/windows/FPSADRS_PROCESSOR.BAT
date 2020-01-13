ECHO "FPSADRS_PROCESSOR.BAT"

CD C:\ffp\BATCH\bin\windows\

SET CONFIG="job/addressservice/fpsadrs_processor.xml"

CALL common\RunBatchJob_utf8.BAT %CONFIG% %1 %2 %3 %4 %5