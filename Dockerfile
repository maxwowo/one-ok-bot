#
# Step 1 -Build the JAR file
#
FROM gradle:7.2.0-jdk8 AS builder

# Create build directory
WORKDIR /build

# Copy the project over
COPY . .

# Build fat JAR
RUN gradle shadowJar

#
# Step 2 - Build a lean runtime container
#
FROM openjdk:8-jre-alpine3.9

# Create bot directory
WORKDIR /bot

COPY --from=builder /build/build/libs/one-ok-bot-1.0-all.jar .

ENTRYPOINT ["java", "-jar", "one-ok-bot-1.0-all.jar"]
