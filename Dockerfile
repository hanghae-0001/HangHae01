FROM openjdk:17-alpine AS builder

WORKDIR app
ARG AWS_ACCESS_KEY_ID
ARG AWS_SECRET_ACCESS_KEY

ENV	ACTIVE_PROFILE prod
ENV	AWS_ACCESS_KEY_ID ${AWS_ACCESS_KEY_ID}
ENV	AWS_SECRET_ACCESS_KEY ${AWS_SECRET_ACCESS_KEY}

COPY ./commerce-api/build/libs/commerce-api-0.0.1.jar .
CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} commerce-api-0.0.1.jar
