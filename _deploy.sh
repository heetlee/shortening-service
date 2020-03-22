#!/bin/sh

set -xe

sudo su
mkdir /home/app && mkdir /home/app/deploy
BASE_HOME=/home/app
DEPLOY_HOME=/home/app/deploy
cd ${DEPLOY_HOME}
git clone https://github.com/heetlee/shortening-service.git
cd shortening-service
mvn clean package