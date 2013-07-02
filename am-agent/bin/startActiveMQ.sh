echo $1
export WORK_PATH=$1
export WORK_VERSION=$2
if [ -z "$WORK_PATH" ]; then
	WORK_PATH=`pwd`/mq
fi
if [ -z "$WORK_VERSION" ]; then
	WORK_VERSION="5.8.0"
fi

if [ ! -d "$WORK_PATH" ]; then  
	mkdir $WORK_PATH  
fi  

cd $WORK_PATH

if [ ! -f "$WORK_PATH/apache-activemq-$WORK_VERSION.zip" ]; then  
	wget http://192.168.1.201/apache-activemq-$WORK_VERSION.zip
	chmod 777 apache-activemq-$WORK_VERSION.zip
fi 
if [ ! -d "$WORK_PATH/apache-activemq-$WORK_VERSION" ]; then  
	unzip apache-activemq-$WORK_VERSION.zip
	chmod 777 apache-activemq-$WORK_VERSION -R
fi 

cd apache-activemq-$WORK_VERSION
./bin/activemq start