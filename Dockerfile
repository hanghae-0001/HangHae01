FROM openjdk:17-alpine AS builder

WORKDIR app

COPY ./build/libs/HangHae01-0.0.1-SNAPSHOT.jar .
CMD java -jar HangHae01-0.0.1-SNAPSHOT.jar
