FROM eclipse-temurin:17-jdk-alpine

ARG APP_HOME=/app

WORKDIR $APP_HOME

COPY build/app.jar $APP_HOME/app.jar

ENTRYPOINT java $JAVA_OPTS -jar app.jar $JAVA_ARGS