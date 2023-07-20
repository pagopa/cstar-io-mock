FROM ghcr.io/graalvm/native-image:ol8-java17-22@sha256:3210af2c1bdf6a73cd82092909e5ed4674ef09caa1b32f2b05db7f232aba72f2 AS builder

RUN microdnf install -y findutils

COPY . .

RUN ./gradlew nativeCompile -Pnative

FROM ubuntu:jammy-20230624@sha256:0bced47fffa3361afa981854fcabcd4577cd43cebbb808cea2b1f33a3dd7f508 AS runtime

EXPOSE 8080

RUN useradd --uid 10000 runner
USER 10000

COPY --from=builder /app/build/native/nativeCompile/cstar-io-mock .

ENTRYPOINT ["./cstar-io-mock"]