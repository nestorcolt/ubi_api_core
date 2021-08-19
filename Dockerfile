# 1 - Container to build the app using gradle 7.2
FROM gradle:jdk11-hotspot as builder

ENV APP_HOME=/home/gradle/api_root
RUN mkdir -p ${APP_HOME}
WORKDIR ${APP_HOME}

RUN mkdir src 
COPY src ${APP_HOME}/src
COPY build.gradle settings.gradle gradle.properties ${APP_HOME}/

RUN gradle microbundle

# 2- Container to create a minimal JRE package
FROM alpine:latest as packager

RUN apk --no-cache add openjdk11-jdk openjdk11-jmods
ENV JAVA_MINIMAL="/opt/java-minimal"

# build minimal JRE with only necesary dependencies
RUN /usr/lib/jvm/java-11-openjdk/bin/jlink \
    --verbose \
    --add-modules \
        java.base,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument \
    --compress 2 --strip-debug --no-header-files --no-man-pages \
    --release-info="add:IMPLEMENTOR=radistao:IMPLEMENTOR_VERSION=radistao_JRE" \
    --output "$JAVA_MINIMAL"

# 3 - Spin the the app container with the packaged light weight JRE
FROM alpine:latest

# Set up the java home environment variables using the JRE from the previous packager container
ENV JAVA_HOME=/opt/java-minimal
ENV PATH="$PATH:$JAVA_HOME/bin"
COPY --from=packager "$JAVA_HOME" "$JAVA_HOME"

ENV APP_HOME=/home/gradle/api_root
WORKDIR /app
COPY --from=builder ${APP_HOME}/build/libs/ROOT-microbundle.jar /app

EXPOSE 8080
CMD java -jar ROOT-microbundle.jar