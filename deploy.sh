#!/bin/bash

# Stop all NginxX processes before launch to clean up
sudo kill $(ps aux | grep '[n]ginx' | awk '{print $2}') > /dev/null 2>&1
sudo docker kill api-gradle > /dev/null 2>&1
sudo docker kill crm_db > /dev/null 2>&1
sudo docker kill frontend > /dev/null 2>&1

# Get parameters
no_dependencies=false
detached=false
force=false
test=false

while getopts d:t:n:f: flag
do
    # shellcheck disable=SC2220
    case "${flag}" in
        d) detached=${OPTARG};;
        t) test=${OPTARG};;
        n) no_dependencies=${OPTARG};;
        f) force=${OPTARG};;
    esac
done

# Console log DRY
message_and_exit() {
    #Will output a message when is successful otherwise will throw and error message
    message=$1
    exec_result=$2

    if [ $exec_result = 0 ]; then
        echo ${message}
    else
      echo "Something Happened running the deployment. Operation failed."
    fi

    exit
}

# Run integration tests with gradle
test_result=false

if [ ${test} = true ]; then
    ./gradlew --rerun-tasks test

    if [ $? = 0 ]; then
        test_result=true
    fi

    echo  "Running app integration test ..."
fi

if [ ${test_result} = false ] && [ ${force} = false ]; then
    echo "Integration test failed catastrophically. Build & deployment canceled."
    exit
fi

# Launch command with no dependencies
if [ ${no_dependencies} = true ]; then
    sudo docker run --rm -it $(sudo docker build .)
    message_and_exit "Running app service with no dependencies ..." $?
fi

# Launch docker-compose to deploy
if [ ${no_dependencies} = false ]; then
  if [ ${detached} = true ]; then
        sudo docker-compose up --detach --build
        message_and_exit "Deployment of the packaged in detached mode." $?
  else
        sudo docker-compose up --build
        message_and_exit "Deployment of de package successful!" $?
  fi
fi
