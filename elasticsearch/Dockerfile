FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
COPY target/elasticsearch*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]