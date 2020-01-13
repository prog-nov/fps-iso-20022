#Auth: Deng Zhi Ming
#Create Date: 20110607

mydate=`date +%Y%m%d`
myhost=`hostname -s`
IFS=:
while :
do
  #clear the screen
  tput clear
  echo "                +----------------------------------------+                    "
  echo "                |CORPORATE BANKING SYSTEM NETWORK CONTROL| 									  "
  echo "                +----------------------------------------+                    "
  echo "______________________________________________________________________________"
  echo "            Host: $myhost                          Date: $mydate              "
  echo "______________________________________________________________________________"
  echo "                          1: System Information Inquiry                       "
  echo "______________________________________________________________________________"
  echo "                          2: Sign On Table Inquiry                            "
  echo "______________________________________________________________________________"
  echo "                          3: Transaction Control                              "
  echo "______________________________________________________________________________"
  echo "                          4: Set on/off Typhoon Mode                          "
  echo "______________________________________________________________________________"
  echo "                          5: Update  ACCOUNT DATE                             "
  echo "______________________________________________________________________________"
  echo "                          6: Update Batch ACCOUNT DATE                        "
  echo "______________________________________________________________________________"
  echo "                          7: Update Online ACCOUNT DATE                       "
  echo "______________________________________________________________________________"
  echo "                          8: Change System Status                             "
  echo "______________________________________________________________________________"
  echo "                          9: Reset CIB Abend Transaction                      "
  echo "______________________________________________________________________________"
  echo "                          A: Abend Task Inquiry                              "
  echo "______________________________________________________________________________"
  echo "                          B: Run MDP Script                                  "
  echo "______________________________________________________________________________"
  echo "                          C: Create a empty File                              "
  echo "______________________________________________________________________________"
	echo "                          D: Resend File                                     "
  echo "______________________________________________________________________________"
	echo "                          E: Rerun Job                                       "
  echo "______________________________________________________________________________"
	echo "                          F: WTS CUT OFF                                      "
  echo "______________________________________________________________________________"
	echo "                          G: CORPORATE SDI CONTROL                            "
  echo "______________________________________________________________________________"
  echo "                          Q: exit                                             "
  echo "______________________________________________________________________________"
  echo "Please Choice Function [1~9,A-G,Q] >"
  read choice
  clear
	case $choice in 
	1)
			#set job config file
			JOB_CONFIG="1"

			#run $CIB_BATCH_BIN/CibOprMan.sh
			$CIB_BATCH_BIN/CibOprMan.sh $JOB_CONFIG $1 $2 $3
			read kk
		;;
	2)
	 		#set job config file
			JOB_CONFIG="2"

			#run $CIB_BATCH_BIN/CibOprMan.sh
			$CIB_BATCH_BIN/CibOprMan.sh $JOB_CONFIG $1 $2 $3
			read kk
		;;
	3)
	 		#set job config file
			JOB_CONFIG="3"

			#run $CIB_BATCH_BIN/CibOprMan.sh
			$CIB_BATCH_BIN/CibOprMan.sh $JOB_CONFIG $1 $2 $3
			read kk
		;;
	4)
	 		#set job config file
			JOB_CONFIG="4"

			#run $CIB_BATCH_BIN/CibOprMan.sh
			$CIB_BATCH_BIN/CibOprMan.sh $JOB_CONFIG $1 $2 $3
			read kk
		;;
	5)
	 		#set job config file
			JOB_CONFIG="5"

			#run $CIB_BATCH_BIN/CibOprMan.sh
			$CIB_BATCH_BIN/CibOprMan.sh $JOB_CONFIG $1 $2 $3
			read kk
		;;
	6)
	 		#set job config file
			JOB_CONFIG="6"

			#run $CIB_BATCH_BIN/CibOprMan.sh
			$CIB_BATCH_BIN/CibOprMan.sh $JOB_CONFIG $1 $2 $3
			read kk
		;;
	7)
	 		#set job config file
			JOB_CONFIG="7"

			#run $CIB_BATCH_BIN/CibOprMan.sh
			$CIB_BATCH_BIN/CibOprMan.sh $JOB_CONFIG $1 $2 $3
			read kk
		;;
	8)
	 		#set job config file
			JOB_CONFIG="8"

			#run $CIB_BATCH_BIN/CibOprMan.sh
			$CIB_BATCH_BIN/CibOprMan.sh $JOB_CONFIG $1 $2 $3
			read kk
		;;
	9)
	 		#set job config file
			JOB_CONFIG="9"

			#run $CIB_BATCH_BIN/CibOprMan.sh
			$CIB_BATCH_BIN/CibOprMan.sh $JOB_CONFIG $1 $2 $3
			read kk
		;;	
	A|a)
	 		#set job config file
			JOB_CONFIG="A"

			#run $CIB_BATCH_BIN/CibOprMan.sh
			$CIB_BATCH_BIN/CibOprMan.sh $JOB_CONFIG $1 $2 $3
			read kk
		;;				
	B|b)
	 		echo "will exe cibmdp01.sh,please input choice:[Y|y--execute other--return]"
	 		read key
	 		if [[ "Y" = $key || "y" = $key ]]
	 			then $CIB_BATCH_BIN/cibmdp01.sh
	 			if [ $? != 0 ]
	 			   then echo execute $jobid fail
	 			else
	 				 echo execute $jobid success
	 			fi
	 		fi
	 		echo " PLS PRESS ANY KEY TO RETURN TO MENU										"
	 		read kk
		;;				
	C|c)
	 		#set job config file
			JOB_CONFIG="C"

			#run $CIB_BATCH_BIN/CibOprMan.sh
			$CIB_BATCH_BIN/CibOprMan.sh $JOB_CONFIG $1 $2 $3
			read kk
		;;				
	D|d)
	 		#set job config file
			JOB_CONFIG="D"

			#run $CIB_BATCH_BIN/CibOprMan.sh
			$CIB_BATCH_BIN/CibOprMan.sh $JOB_CONFIG $1 $2 $3
			read kk
		;;				
	E|e)
	 		echo "+-------------------------------------------------------+"
	 		echo "|         CORPORATE RERUN JOB		    				  |"
	 		echo "+-------------------------------------------------------+"
	 		echo "E. RERUN JOB:"
	 		echo "PLEASE INPUT JOB ID-->"
	 		read jobid
	 		lower=`echo $jobid |tr A-Z a-z`
	 		#echo $lower
	 		exejob="${lower}.sh"
	 		echo "PLEASE INPUT AC DATE(DEFAULT BATCH ACDATE OF TOTAL)-->"
	 		read acdate
	 		if [[ "" != $acdate ]]
	 			then exejob="$exejob -D $acdate"
	 		fi
	 		echo "now execute $exejob"
	 		echo "PLS CHECK RUN SCRIPT [Y:OK N:RETURN TO MENU ]--> "
	 		read key
	 		if [[ "Y" = $key || "y" = $key ]]
	 			then $CIB_BATCH_BIN/$exejob
	 			if [ $? != 0 ]
	 			   then echo execute $jobid fail
	 			else
	 				 echo execute $jobid success
	 			fi
	 		fi
	 		echo "PLS PRESS ANY KEY TO RETURN TO MENU-->"
	 		read kk
		;;				
	F|f)
	 		#set job config file
			JOB_CONFIG="F"

			#run $CIB_BATCH_BIN/CibOprMan.sh
			$CIB_BATCH_BIN/CibOprMan.sh $JOB_CONFIG $1 $2 $3
			read kk
		;;	
	G|g)
	 		#set job config file
			JOB_CONFIG="G"

			#run $CIB_BATCH_BIN/CibOprMan.sh
			$CIB_BATCH_BIN/CibOprMan.sh $JOB_CONFIG $1 $2 $3
			read kk
		;;						
  Q|q)
    break
    ;;
  *)
     echo error instruction,pls check,any key expect space to continue
     read key
     continue
     ;;
  esac
done