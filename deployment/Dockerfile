FROM eclipse-temurin:21-jdk-alpine
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
RUN mkdir -p /logs && chown -R appuser:appgroup /logs
VOLUME /tmp
COPY ../applications/app-service/build/libs/CrediyaReportsMS.jar CrediyaReportsMS.jar

# Añadir la zona horaria para evitar desincronizacion con los servidores de Amazon
ENV TZ=America/Bogota
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"
USER appuser
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS  -jar CrediyaReportsMS.jar" ]