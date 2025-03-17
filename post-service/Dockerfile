FROM openjdk:17-jdk-alpine
WORKDIR /app

# Copy only what's needed from the root
COPY gradle ./gradle
COPY gradlew .
COPY gradlew.bat .
COPY settings.gradle .
COPY build.gradle .

# Copy the service-specific code
COPY post-service ./post-service

# Build from the service directory
WORKDIR /app/post-service
RUN ../gradlew build

# Expose the proper port
EXPOSE 8083

# Run the application
# Find and use the actual JAR file name
CMD java -jar $(find build/libs/ -name '*.jar' ! -name '*plain.jar' | head -n 1)