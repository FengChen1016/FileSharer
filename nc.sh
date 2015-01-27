#!/bin/bash
currentDate=`date`
t0=`date -d $currentDate +%s`  # convert to long timestamp

echo "$0 $$ Start network checking..."

#address=192.168.0.119  // not exist for debug
address=www.baiduc.com
reportFile=report.txt
maxSize=(( 1024 * 1024))  # 1 Mb
duration=3600  # 1 hour
total=1
num=1
while true
do
	result=`ping -c 1 ${address}`
	total=`expr ${total} + 1`
	returnCode=$?
	# if [[ $string == *"some string"* ]]
	if [[ ${returnCode} -eq 0 ]] 
	then
		# ping success, do nothing
	else
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
	if (( `expr $t1 + $duration` > $t0 )) || (( $reportSize > $maxSize ))
    then
        break
    fi
done
return 0
