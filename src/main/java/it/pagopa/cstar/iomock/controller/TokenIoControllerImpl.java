package it.pagopa.cstar.iomock.controller;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import it.pagopa.cstar.iomock.dto.IoUser;
import it.pagopa.cstar.iomock.service.TokenIoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@Slf4j
public class TokenIoControllerImpl implements TokenIoController {

  private final WebClient.Builder webClientBuilder;
  private final TokenIoService tokenIoService;

  @Autowired
  public TokenIoControllerImpl(Builder webClientBuilder, TokenIoService tokenIoService) {
    this.webClientBuilder = webClientBuilder;
    this.tokenIoService = tokenIoService;
  }

  @Override
  public Mono<ResponseEntity<String>> createToken(String version, String fiscalCode) {
    return Mono.just(fiscalCode)
        .subscribeOn(Schedulers.boundedElastic())
        .map(tokenIoService::generateToken)
        .map(ResponseEntity::ok)
        .doOnError(error -> log.error("Error during token creation", error));
  }

  @Override
  public Mono<ResponseEntity<IoUser>> introspectToken(String version, String token) {
    return Mono.fromCallable(() -> tokenIoService.getIoUser(token))
        .subscribeOn(Schedulers.boundedElastic())
        .map(ResponseEntity::ok)
        .doOnError(error -> log.error("Error during token introspection", error))
        .onErrorReturn(MalformedJwtException.class, new ResponseEntity<>(HttpStatus.BAD_REQUEST))
        .onErrorReturn(JwtException.class, new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
  }

  @Override
  public Mono<ResponseEntity<String>> createTokenTest(String version, String fiscalCode) {
    return webClientBuilder.baseUrl("https://google.it")
        .build()
        .get()
        .retrieve()
        .bodyToMono(String.class)
        .flatMap(a -> Mono.just(fiscalCode)
            .subscribeOn(Schedulers.boundedElastic())
            .map(tokenIoService::generateToken)
            .map(ResponseEntity::ok)
            .doOnError(error -> log.error("Error during token creation", error)));
  }
}
