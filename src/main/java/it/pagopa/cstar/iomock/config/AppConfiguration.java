package it.pagopa.cstar.iomock.config;

import it.pagopa.cstar.iomock.service.TokenIoService;
import it.pagopa.cstar.iomock.service.TokenIoServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

  @Bean
  TokenIoService tokenIoService(
      @Value("${iobackend.jwtkey}") String jwtKey
  ) {
    return new TokenIoServiceImpl(jwtKey);
  }
}
