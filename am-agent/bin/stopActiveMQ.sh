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

cd apache-activemq-$WORK_VERSION
./bin/activemq stop
