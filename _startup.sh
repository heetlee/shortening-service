#!/bin/sh

set -xe

WORKSPACE_HOME=/home/app/deploy/shortening-service/target
java -jar ${WORKSPACE_HOME}/shortening-service.jar