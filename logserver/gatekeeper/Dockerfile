FROM openjdk:8u191-alpine

COPY . /app
WORKDIR /app

RUN ./gradlew build

CMD ["./gradlew", "run"]