FROM openjdk:17-alpine AS builder

WORKDIR app

ENV	ACTIVE_PROFILE prod

COPY ./build/libs/HangHae01-0.0.1-SNAPSHOT.jar .
CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} HangHae01-0.0.1-SNAPSHOT.jar
