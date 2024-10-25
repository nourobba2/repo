FROM openjdk:8-jdk-alpine

EXPOSE 8082

COPY target/timesheet-devops-1.0.jar timesheet-devops-1.0.jar

ENTRYPOINT ["java","-jar","/timesheet-devops-1.0.jar"]