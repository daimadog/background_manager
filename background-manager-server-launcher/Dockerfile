FROM openjdk:8
MAINTAINER dlm
EXPOSE 8099
RUN ln -snf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
ADD background-manager-server-launcher-1.0-SNAPSHOT.jar background-manager-server.jar
ENTRYPOINT ["java","-XX:+UseContainerSupport","-XX:+UseG1GC","-jar","background-manager-server.jar","--spring.profiles.active=dev"]
