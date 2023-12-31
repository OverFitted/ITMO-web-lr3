#!/bin/bash

echo "Building project"

./mvnw clean install

echo "Deploying to Helios"

## Remove existing deployment
ssh -p 2222 s387612@se.ifmo.ru "rm -rf wildfly-26.1.3/standalone/deployments/eclipselink-orm-jsf-demo-1.0-SNAPSHOT.war"
# add new deployment
scp -P 2222 ./target/eclipselink-orm-jsf-demo-1.0-SNAPSHOT.war s387612@se.ifmo.ru:wildfly-26.1.3/standalone/deployments
