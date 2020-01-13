ECHO "OUTWARD_FPSPCRO_GENERATOR.BAT"

CD C:\ffp\BATCH\bin\windows\

SET CONFIG="job/participant/fpspcrogenerator/outward_fpspcro_generator.xml"

CALL common\RunBatchJob_utf8.BAT %CONFIG% %1 %2 %3 %4 %5