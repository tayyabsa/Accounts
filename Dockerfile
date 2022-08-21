FROM adoptopenjdk/openjdk11
MAINTAINER tayyabsa@hotmail.com
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/accounts-0.0.1-SNAPSHOT.jar"]