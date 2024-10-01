#
# Step 1 -Build the JAR file
#
FROM gradle:8.10.2-jdk17 AS builder

# Create build directory
WORKDIR /build

# Copy the project over
COPY . .

# Build fat JAR
RUN gradle shadowJar

#
# Step 2 - Build a lean runtime container
#
FROM amazoncorretto:23.0.0-al2023-headless

# Install Doppler CLI
RUN rpm --import 'https://packages.doppler.com/public/cli/gpg.DE2A7741A397C129.key' && \
    curl -sLf --retry 3 --tlsv1.2 --proto "=https" 'https://packages.doppler.com/public/cli/config.rpm.txt' | tee /etc/yum.repos.d/doppler-cli.repo && \
    yum update -y && \
    yum install -y doppler

# Create bot directory
WORKDIR /bot

COPY --from=builder /build/build/libs/one-ok-bot-1.0-all.jar .

CMD ["doppler", "run", "-c", "prd", "--", "java", "-jar", "one-ok-bot-1.0-all.jar"]
