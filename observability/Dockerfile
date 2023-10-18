FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY opentelemetry/opentelemetry-javaagent.jar ot.jar
COPY target/*.jar app.jar
ENTRYPOINT ["java","-javaagent:/ot.jar", "-Dotel.service.name=obs-service", "-Dotel.exporter.otlp.protocol=grpc", "-Dotel.exporter.otlp.endpoint=http://tempo:4317/", "-Dotel.metrics.exporter=prometheus", "-jar","/app.jar"]