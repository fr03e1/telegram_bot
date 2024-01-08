ARG ENVIRONMENT='test'

# BUILDER
FROM maven:3.8.5-openjdk-17 AS builder
ARG ENVIRONMENT
WORKDIR /app

COPY pom.xml ./

RUN --mount=type=cache,target=/root/.m2 \
    mvn dependency:go-offline

COPY . .

RUN --mount=type=cache,target=/root/.m2 \
    mvn clean package -P ${ENVIRONMENT}

# RUNNER
FROM openjdk:17
WORKDIR /app

COPY --from=builder /app/target/pet.jar ./

ENTRYPOINT ["java", "-jar", "/app/pet.jar"]