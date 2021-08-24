#!/bin/bash

# Set of helper functions to avoid repetition
clean_up_before_continue() {
  # Stop all NginxX processes before launch to clean up
  sudo docker kill api-gradle >/dev/null 2>&1
  sudo docker kill crm_db >/dev/null 2>&1
  sudo docker kill frontend >/dev/null 2>&1
}

# Console log DRY
message_and_exit() {
  #Will output a message when is successful otherwise will throw and error message
  message=$1
  exec_result=$2

  if [ $exec_result = 0 ]; then
    echo ${message}
  else
    echo "Something Happened running the deployment. Operation failed."
    exit 1
  fi

  exit 0
}

# Start with a clean up
clean_up_before_continue

# Get parameters
no_dependencies=false
detached=false
force=false
test=false

while getopts d:t:n:f: flag; do
  # shellcheck disable=SC2220
  case "${flag}" in
  d) detached=${OPTARG} ;;
  t) test=${OPTARG} ;;
  n) no_dependencies=${OPTARG} ;;
  f) force=${OPTARG} ;;
  esac
done

# Run integration tests with gradle
test_result=false
should_continue_exec=false

if [ ${test} = true ]; then
  ./gradlew --rerun-tasks test

  if [ $? = 0 ]; then
    test_result=true
  fi

  echo "Running app integration test ..."
else
  # The test was not run
  should_continue_exec=true
fi

# Handle if any argument was sent on the deploy.sh invocation to ignore the test but avoid a "fake test failure"
if [ ${should_continue_exec} = false ]; then
  if [ ${test_result} = false ] && [ ${force} = false ]; then
    echo "Integration test failed catastrophically. Build & deployment canceled."
    exit 0
  fi
fi

# Clean up after test using the utp ports
clean_up_before_continue

# Launch command with no dependencies (only JAR file will be run in docker container)
if [ ${no_dependencies} = true ]; then
  sudo docker run --rm -it $(sudo docker build .)
  message_and_exit "Running app service with no dependencies ..." $?
fi

# Launch docker-compose to deploy all the microservices
if [ ${no_dependencies} = false ]; then
  if [ ${detached} = true ]; then
    sudo docker-compose up --detach --build
    message_and_exit "Deployment of the packaged in detached mode." $?
  else
    sudo docker-compose up --build
    message_and_exit "Deployment of de package successful!" $?
  fi
fi
