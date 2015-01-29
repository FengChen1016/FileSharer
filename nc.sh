#!/bin/bash

### information ###
currentDate=`date`
t0=`date -d "$currentDate" +%s`  # convert to long timestamp
echo "$0 $$ Start network checking..."

### variable definition and preparation ###
#address=192.168.0.119  # not exist for debug
address=www.baiduc.com
reportFile=report.txt
touch $reportFile  # make sure this file exist
# cat /dev/null > $reportFile
maxSize=`expr 1024 \* 1024`  # 1 Mb
duration=3600  # 1 hour
total=1
num=1

### execute ###
while true
do
	total=`expr ${total} + 1`
	result=`ping -c 1 ${address}`
	returnCode=$?
	# if [[ $string == *"some string"* ]]
	if [[ ${returnCode} -ne 0 ]] 
	then
		num=`expr ${num} + 1`
		echo "${num}/${total} : ${result}" >> $reportFile
	fi
	#sleep one second
	sleep 1s
	t1=`date +%s`
	#tt=`expr $t1 + 3600`  # one hour
	# use [[ ]] to compare strings, use (( )) to compare numbers
	#Get file size in bytes
	reportSize=`stat -c%s $reportFile`
	# break if runing for specified time or log file size > maxSize
	if (( `expr $t0 + $duration` < $t1 )) || (( $reportSize > $maxSize ))
    then
        break
    fi
done
