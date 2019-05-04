FROM java:8
VOLUME /tmp
ADD auth-core-1.0-SNAPSHOT.jar auth-server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/auth-server.jar"]
EXPOSE 8201
