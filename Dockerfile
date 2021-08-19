# 1 - Container to build the app using gradle 7.2
FROM gradle:jdk11-hotspot as builder

ENV APP_HOME=/home/gradle/api_root
RUN mkdir -p ${APP_HOME}
WORKDIR ${APP_HOME}

RUN mkdir src 
COPY src ${APP_HOME}/src
COPY build.gradle settings.gradle gradle.properties ${APP_HOME}/

RUN gradle microbundle

# 2 - Spin the the app container 
FROM openjdk:11.0.9-jre-slim-buster

ENV APP_HOME=/home/gradle/api_root
WORKDIR /app
COPY --from=builder ${APP_HOME}/build/libs/ROOT-microbundle.jar /app

EXPOSE 8080
CMD java -jar ROOT-microbundle.jar