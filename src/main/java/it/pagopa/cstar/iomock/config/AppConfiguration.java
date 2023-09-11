package it.pagopa.cstar.iomock.config;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.instrumentation.spring.webflux.v5_3.SpringWebfluxTelemetry;
import it.pagopa.cstar.iomock.service.TokenIoService;
import it.pagopa.cstar.iomock.service.TokenIoServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.WebFilter;

@Configuration
public class AppConfiguration {

  private final SpringWebfluxTelemetry webfluxTelemetry;

  public AppConfiguration(OpenTelemetry openTelemetry) {
    this.webfluxTelemetry = SpringWebfluxTelemetry.builder(openTelemetry).build();
  }

  @Bean
  TokenIoService tokenIoService(
      @Value("${iobackend.jwtkey}") String jwtKey
  ) {
    return new TokenIoServiceImpl(jwtKey);
  }

  @Bean
  public WebFilter webFilter() {
    return webfluxTelemetry.createWebFilterAndRegisterReactorHook();
  }
}
