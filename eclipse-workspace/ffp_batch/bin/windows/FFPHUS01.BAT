ECHO "FFPHUS01.BAT"

CD C:\ffp\BATCH\bin\windows\

SET CONFIG="husjob/ffphus01/ffp-hus01.xml"

call common\RunBatchJob_utf8.BAT %CONFIG% %1 %2 %3 %4 %5


