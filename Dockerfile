FROM openjdk
WORKDIR /basicecom
COPY ./build/libs/basicecom.jar .
EXPOSE 8080
CMD ["java","-jar","./basicecom.jar"]