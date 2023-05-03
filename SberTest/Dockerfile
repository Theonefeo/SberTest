FROM adoptopenjdk/openjdk11:alpine-jre  
ARG JAR_FILE=target/SberTest-0.0.1-SNAPSHOT.jar  
WORKDIR ./fordocker
COPY ${JAR_FILE} app.jar  
ENTRYPOINT ["java","-jar","app.jar"]  
