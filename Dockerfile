# 基础镜像
FROM openjdk:8
MAINTAINER laizuqi

COPY ./target/rest-service-0.0.1-SNAPSHOT.jar /
EXPOSE 8080
CMD java -jar rest-service-0.0.1-SNAPSHOT.jar



#docker build -t rest-service .
#docker run -d -p 8080:8080 rest-service
