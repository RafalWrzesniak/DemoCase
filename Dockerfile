FROM openjdk:11-jre-slim
MAINTAINER rafal.wrzesniak
COPY myapp-1.0-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]
#RUN apt-get update
#RUN apt-get install -y curl