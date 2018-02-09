#!/bin/bash

cd ../DockerLPro/WildflyAdmin
docker build -t lpro/wildfly-admin .
cd ../Postgres
docker build -t lpro/pg11 .
cd ../../jee
mvn clean install
sudo cp target/geoquizz.war ../DockerLPro/deployments