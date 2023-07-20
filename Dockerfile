FROM ghcr.io/graalvm/native-image:ol8-java17-22 AS builder
RUN microdnf install -y findutils
COPY . .
RUN ./gradlew nativeCompile
FROM ubuntu:lunar-20230128 as runtime
EXPOSE 8080

COPY --from=builder /app/build/native/nativeCompile/cstar-io-mock .

ENTRYPOINT ["./cstar-io-mock"]