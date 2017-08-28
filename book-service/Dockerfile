FROM java:8-jre

ADD ./target/book-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/book-service.jar"]

EXPOSE 6000