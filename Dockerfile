# CONDIGURE
FROM openjdk:19-jdk-alpine
VOLUME /tmp
WORKDIR /app

# COPY JAR FILE
COPY ./build/libs/*.jar /app/app.jar

#RUN
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]