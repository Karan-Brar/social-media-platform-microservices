FROM openjdk:17-jdk-alpine
WORKDIR /app

# Copy only what's needed from the root
COPY gradle ./gradle
COPY gradlew .
COPY gradlew.bat .
COPY settings.gradle .
COPY build.gradle .

# Copy the service-specific code
COPY like-service ./like-service

# Build from the service directory
WORKDIR /app/like-service
RUN ../gradlew build

# Expose the proper port
EXPOSE 8086

# Run the application
# Find and use the actual JAR file name
CMD java -jar $(find build/libs/ -name '*.jar' ! -name '*plain.jar' | head -n 1)