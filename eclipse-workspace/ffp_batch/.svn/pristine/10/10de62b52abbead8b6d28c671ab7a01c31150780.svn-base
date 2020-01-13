#################################################
#script name:risec1.sh
#
#################################################

#################################################
# Special dispose
#. $RIS_BATCH_BIN/risdbmanager.sh

#ConnectDB
#if [ $? = "0" ]
#	then
#  	echo "删除D_OVERSEAS_TX_HISTORY_TEMP中间表的数据"
#  	waitEcgLog 0 "删除D_OVERSEAS_TX_HISTORY_TEMP中间表的数据"
#  	db2 "load client from $RIS_BATCH_CONFINFO/job/risec1/D_OVERSEAS_TX_HISTORY_TEMP.ixf of ixf replace into RTCXU01.D_OVERSEAS_TX_HISTORY_TEMP"
#  	if [ $? = "0" ]
#  	then
#  		echo "删除D_OVERSEAS_TX_HISTORY_TEMP中间表的数据成功"
#  		waitEcgLog 0 "删除D_OVERSEAS_TX_HISTORY_TEMP中间表的数据成功"
#  		DisconnectDB
#  	else
#  		echo "删除D_OVERSEAS_TX_HISTORY_TEMP中间表的数据失败"
#  		waitEcgLog 0 "删除D_OVERSEAS_TX_HISTORY_TEMP中间表的数据失败"
#  		DisconnectDB
#  		return 1
#  	fi
#fi
#################################################
CONFIG="job/risec1/ris-ec1.xml"

sh $RIS_BATCH_BIN/common/RunBatchJob.sh $CONFIG $1 $2 $3 $4 $5

