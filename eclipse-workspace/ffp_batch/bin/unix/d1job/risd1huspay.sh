#################################################
#script name:risd1huspay.sh
#
#################################################
CONFIG="d1job/rishuspay/ris-hus-pay.xml"

sh $RIS_BATCH_BIN/common/RunBatchJob.sh $CONFIG $1 $2 $3 $4 $5

