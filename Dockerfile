FROM java:8
ADD target/skill-service.jar .
EXPOSE 8001
CMD java -jar -Xmx512M skill-service.jar