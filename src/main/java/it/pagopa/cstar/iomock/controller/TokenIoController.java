package it.pagopa.cstar.iomock.controller;

import it.pagopa.cstar.iomock.dto.IoUser;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

@RequestMapping(value = "/bpd/pagopa/api/v1")
public interface TokenIoController {

  @PostMapping(value = "/login", produces = {"application/json"})
  Mono<ResponseEntity<String>> createToken(
      @RequestParam(value = "version", required = false) String version,
      @Valid @NotNull @NotBlank @RequestParam("fiscalCode") String fiscalCode
  );

  @GetMapping(value = "/user", produces = {"application/json"})
  Mono<ResponseEntity<IoUser>> introspectToken(
      @RequestParam(value = "version", required = false) String version,
      @Valid @NotNull @NotBlank @RequestParam(value = "token") String token
  );
}
