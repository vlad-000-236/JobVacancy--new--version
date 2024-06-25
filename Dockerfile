FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY /out/artifacts/JobVacancyTestTask_jar/JobVacancyTestTask.jar /app/testTask.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "testTask.jar"]