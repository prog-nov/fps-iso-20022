################################################################################
# $RIS_BATCH_BIN/Splitfile.sh
#
# UpdateDate:2012/01/17
# ModifiedBy:Eled
# ModifiedReason: update regular expression
# UpdateVersion:4 Month
################################################################################
. $RIS_BATCH_BIN/common/SetEnv.sh

ftpTmpCfg=$RIS_BATCH_LOG/tmpftp$$.cfg            

flag=N
JOBCONFIG_PATH=$RIS_BATCH_ROOT/conf/job/unit


echo "pls input the target iff name(case sensitive):"
read iffName
echo seaching...
`egrep -r -l '.*<file-name>#{(in|out)putFilePath}/(<!\[CDATA\[)?'$iffName'(\]\]>)?</file-name>.*'  $JOBCONFIG_PATH > $ftpTmpCfg`
cat -n $ftpTmpCfg
count=`cat $ftpTmpCfg | wc -l`
while [ "$flag" == "N" ]
do 
	if [ $count -gt 1 ]
	then
		echo "pls input the line number:"
		read lineNumber
		if [ $lineNumber -ge 1 -a  $lineNumber -le $count ] 
		then 
		     flag=Y
		     iffConfigPath=`cat $ftpTmpCfg | sed -n $lineNumber'p'`
		else
			echo "the input number is invalid,pls check"
		fi
	elif [ $count -eq 0 ]
	then
		echo "can't find the config file,pls check"
		rm -f $ftpTmpCfg
		return 1
	else
		flag=Y 
		iffConfigPath=`cat $ftpTmpCfg | sed -n '1p'`
	fi 
done
rm -f $ftpTmpCfg

flag=N
while [ "$flag" == "N" ]
do
	echo "pls input query type:\n00(query all with no condition)\n01(query all with condition)\n10(query column with no condition)\n11(query column with condition)"
	read queryType 
	confirmFlag=`echo $queryType | egrep '^(0|1)(0|1)$'`
	if [ ! -z "$confirmFlag" ] 
	then 
	     flag=Y
	fi
done

if [ "$queryType" == "10" ]
then 
		echo "pls input columns as format: field1:field2..."
		read columnsInfo
elif [ "$queryType" == "11" ]
then
		echo "pls input conditons as format: field1=value:field2=value:field3:field4..."
		read columnsInfo
elif [ "$queryType" == "01" ]
then 
	echo "pls input conditons as format: field1=value:field2=value..."
	read columnsInfo
else 
	columnsInfo="none"
fi

flag=N
while [ "$flag" == "N" ]
do  
	echo "pls input query lines:\n0(all)\na-b(from line a to b)"
	read queryLines
	confirmFlag=`echo $queryLines | egrep '^(0|[0-9]+-?[0-9]+)'`
	if [ ! -z "$confirmFlag" ] 
	then 
	     flag=Y
	fi
done

flag=N
while [ "$flag" == "N" ]
do 
	echo "pls input inputFile path:"
	read inputFilePath
	if [ -e "$inputFilePath$iffName" ] 
	then 
	     flag=Y
	else
		echo "the directory $inputFilePath$iffName isn't exists,pls check..."
	fi
done

flag=N
while [ "$flag" == "N" ]
do 
echo "pls input outputFile path:"
read outputFilePath
	if [ -e "$outputFilePath" ] 
	then 
	     flag=Y
	else
		echo "the directory $outputFilePath isn't exists,pls check..."
	fi
done

RUN_CLASS="com.forms.framework.util.GenSplitFile"

inputFilePath=$inputFilePath$iffName
outputFilePath=$outputFilePath$iffName.split

# run the JAVA class
java -cp "$JAR_FILE:$LIB_FILE" -Xms32m -Xmx256m $RUN_CLASS "$queryType" "$iffConfigPath" "$columnsInfo" "$queryLines" "$inputFilePath" "$outputFilePath"
