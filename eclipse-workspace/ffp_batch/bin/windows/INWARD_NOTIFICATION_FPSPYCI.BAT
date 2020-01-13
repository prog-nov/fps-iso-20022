ECHO "INWARD_FPSPYCI_RETURNREFUND_PROCESSOR.BAT"

CD C:\ffp\BATCH\bin\windows\

SET CONFIG="job/iclfps/payment/notification/inward_fpspyci_notification_processor.xml"

call common\RunBatchJob_utf8.BAT %CONFIG% %1 %2 %3 %4 %5


