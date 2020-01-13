#################################################
#script name:risd1moverseawithin.sh
#
#################################################
CONFIG="d1job/moverseaswithin/ris-hus-m-overseas-within.xml"

sh $RIS_BATCH_BIN/common/RunBatchJob.sh $CONFIG $1 $2 $3 $4 $5

