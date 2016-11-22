time_stamp=$(date +%Y-%m-%d-%T)
cd /usr/backup
mkdir -p "${time_stamp}"
mv /usr/output/Export.txt /usr/output/backup/${time_stamp}/
cd /usr/logs/
rm -f *.txt
cd /usr/config/
# change existing dd/mm/yyyy format into current time+2 min
awk -v min=$(date +%M) -v hour=$(date +%H) 'NR == 2 { $2 = min+1; $3 = hour } 1' fileName.properties > fileName.properties.new
mv -f fileName.properties.new fileName.properties
cd /usr/src
echo "Running jar command"
java -jar jarName.jar env=E1 useLocalAimFile=false runSqlLoader=false &
pid=$!
echo $!
echo "Going to sleep for 4 mins"
sleep 4m
echo "Woke up!!Checking logs"
#Check logs
cd /usr/logs/
logs=$(ls -t | head -n1)
echo "File Name" ${logs}
   if grep "Batch process completed successfully." ${logs}
   then
                echo "csv files created successfully"
   else
        echo "not found"
   fi
#Kill java process
kill -9 ${pid}
echo "Jar Process is killed"
cd  /usr/backup/
#time_stamp=$(date +%Y-%m-%d-%T)
mkdir -p "${time_stamp}"
mv /usr/loads/*.csv /usr/loads/backup/${time_stamp}/
echo "csv file move to backup"
cd /usr/delta/
java -jar GenerateDeltaFile.jar
cat Delta_File.txt
echo "Check Delta file at /usr/delta/Delta_File.txt"
