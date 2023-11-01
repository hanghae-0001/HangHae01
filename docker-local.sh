#!/bin/sh

echo "docker local container started"

docker-compose -f docker-compose-local.yml down
./gradlew clean build
docker-compose -f docker-compose-local.yml up --build

