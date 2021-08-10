FROM adoptopenjdk:14-jre-hotspot
RUN mkdir /opt/app
COPY target/vizsgagyak-0.0.1-SNAPSHOT.jar /opt/app/vizsgagyak.jar
CMD ["java", "-jar", "/opt/app/vizsgagyak.jar"]