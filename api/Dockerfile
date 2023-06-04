FROM gradle AS builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /
EXPOSE 8080
COPY --from=builder /home/gradle/src/build/libs/*.war /app.war
ENTRYPOINT ["java","-jar","/app.war"]
