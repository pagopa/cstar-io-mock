package it.pagopa.cstar.iomock.config;

import it.pagopa.cstar.iomock.dto.IoUser;
import it.pagopa.cstar.iomock.service.TokenIoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

  @Bean
  TokenIoService tokenIoService() {
    return new TokenIoService() {
      @Override
      public String generateToken(String fiscalCode) {
        return "";
      }

      @Override
      public IoUser getIoUser(String token) {
        return IoUser.builder().build();
      }
    };
  }
}
