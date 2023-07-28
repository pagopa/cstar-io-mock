FROM ghcr.io/graalvm/native-image:ol8-java17-22@sha256:3210af2c1bdf6a73cd82092909e5ed4674ef09caa1b32f2b05db7f232aba72f2 AS builder

RUN microdnf install -y findutils

COPY . .

RUN ./gradlew nativeCompile -Pnative

FROM ubuntu:mantic-20230712@sha256:23e2d6c4de2adfcbd631be39296d8eb6968e2bfbf0df86db88147f2814086bd6 AS runtime

EXPOSE 8080

RUN useradd --uid 10000 runner
USER 10000

COPY --from=builder /app/build/native/nativeCompile/io-mock .

ENTRYPOINT ["./io-mock"]