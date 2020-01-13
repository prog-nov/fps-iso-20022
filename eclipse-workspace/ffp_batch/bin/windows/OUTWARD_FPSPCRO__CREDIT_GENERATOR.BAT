ECHO "OUTWARD_FPSPCRO__CREDIT_GENERATOR.BAT"

CD C:\ffp\BATCH\bin\windows\

SET CONFIG="job\participant\credittransfer\outward_fpspcro_credit_generator.xml"

CALL common\RunBatchJob_utf8.BAT %CONFIG% %1 %2 %3 %4 %5