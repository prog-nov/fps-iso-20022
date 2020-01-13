ECHO "FPSD2006_READER.BAT"

CD C:\ffp\BATCH\bin\windows\

SET CONFIG="job/iclfps/payment/certificate/fpsd2006_reader.xml"

CALL common\RunBatchJob_utf8.BAT %CONFIG% %1 %2 %3 %4 %5