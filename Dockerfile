FROM openjdk:17
EXPOSE 8080
ADD target/kliqmee-orderservice.jar kliqmee-orderservice.jar
ENTRYPOINT [ "java","-jar","kliqmee-orderservice.jar" ]
