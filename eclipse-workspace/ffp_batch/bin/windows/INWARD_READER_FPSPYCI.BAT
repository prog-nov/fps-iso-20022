ECHO "INWARD_READER_FPSPYCI.BAT"

CD C:\ffp\BATCH\bin\windows\

SET CONFIG="job/iclfps/payment/credittransfer/inward_fpspyci_reader.xml"

call common\RunBatchJob_utf8.BAT %CONFIG% %1 %2 %3 %4 %5


