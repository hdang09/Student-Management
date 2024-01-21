FROM openjdk
EXPOSE 8080
ADD target/student.jar student.jar
ENTRYPOINT ["java", "-jar", "/student.jar"]