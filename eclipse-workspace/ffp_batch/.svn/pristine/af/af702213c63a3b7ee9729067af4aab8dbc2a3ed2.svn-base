################################
#
################################
. $RIS_BATCH_BIN/common/Writelog.sh

inputFile=$1
xFilePath=$2

if [ -e $inputFile ]
then
	gzip -df $inputFile
else
	waitEcgLog 2 "file $inputFile not exists"
fi
