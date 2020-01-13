ECHO "HOUSE_KEEPING.BAT"

CD C:\ffp\BATCH\bin\windows\

SET CONFIG="job/housekeeping/house_keeping.xml"

call common\RunBatchJob_utf8.BAT %CONFIG% %1 %2 %3 %4 %5