# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM eclipse-temurin:21-alpine
COPY target/${project.name}.jar /app.jar
# This is the port that your javalin application will listen on
EXPOSE 7000 
ENTRYPOINT ["java", "-jar", "/app.jar"]