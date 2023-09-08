# cstar-io-mock
It mocks a few functionalities of the IO backend to conduct integration tests


## Direct Open Telemetry Instrumentation Setup

Spring Boot Autoconfigure: https://github.com/open-telemetry/opentelemetry-java-instrumentation/tree/main/instrumentation/spring/spring-boot-autoconfigure

Due to missing mandatory data from reactive stack micrometer tracing, we need to add a proper
configuration to span incoming requests. The configuration example reported here https://github.com/open-telemetry/opentelemetry-java-instrumentation/tree/main/instrumentation/spring/spring-webflux/spring-webflux-5.3/library 
allows to instrumentate webflux client and server.