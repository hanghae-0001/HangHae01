FROM openjdk:17-alpine AS builder

WORKDIR app
ARG AWS_ACCESS_KEY_ID=ACCESS_KEY_ID
ARG AWS_SECRET_ACCESS_KEY=SECRET_ACCESS_KEY

ENV	ACTIVE_PROFILE prod

COPY ./commerce-api/build/libs/commerce-api-0.0.1.jar .
CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} -DaccessKeyId=${AWS_ACCESS_KEY_ID} -DsecretAccessKey=${AWS_SECRET_ACCESS_KEY} commerce-api-0.0.1.jar
