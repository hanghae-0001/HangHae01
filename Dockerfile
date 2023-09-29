FROM openjdk:17-alpine AS builder

WORKDIR app

ENV	ACTIVE_PROFILE prod

COPY ./commerce-api/build/libs/commerce-api-0.0.1.jar .
CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} commerce-api-0.0.1.jar
