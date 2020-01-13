#################################################
#script name:rishuspayout.sh
#
#################################################
CONFIG="husjob/rishuspayout/ris-hus-pay-out.xml"

sh $RIS_BATCH_BIN/common/RunBatchJob.sh $CONFIG $1 $2 $3 $4 $5

