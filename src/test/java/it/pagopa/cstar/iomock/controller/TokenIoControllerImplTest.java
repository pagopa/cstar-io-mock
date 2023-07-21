package it.pagopa.cstar.iomock.controller;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import it.pagopa.cstar.iomock.dto.IoUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(TokenIoControllerImpl.class)
class TokenIoControllerImplTest {

  @Autowired
  private WebTestClient client;

  @Test
  void whenCreateNewTokenThenIsValid() {
    final var fiscalCode = "123456ABCD";

    final var token = client.post()
        .uri(builder -> builder
            .path("/bpd/pagopa/api/v1/login")
            .queryParam("fiscalCode", fiscalCode)
            .build()
        )
        .exchange()
        .expectStatus().isOk()
        .expectBody(String.class)
        .value(Assertions::assertNotNull)
        .returnResult()
        .getResponseBody();

    client.get()
        .uri(builder ->
            builder.path("/bpd/pagopa/api/v1/user")
                .queryParam("token", token)
                .build()
        )
        .exchange()
        .expectStatus().isOk()
        .expectBody(IoUser.class)
        .value(v -> assertThat(v.getFiscalCode()).isEqualTo(fiscalCode));
  }

  @Test
  void whenTokenIsInvalidThenUnauthorized() {
    final var token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    client.get()
        .uri(builder ->
            builder.path("/bpd/pagopa/api/v1/user")
                .queryParam("token", token)
                .build()
        )
        .exchange()
        .expectStatus().isUnauthorized();
  }
}