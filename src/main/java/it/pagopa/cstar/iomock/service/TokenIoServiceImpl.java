package it.pagopa.cstar.iomock.service;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import it.pagopa.cstar.iomock.dto.IoUser;
import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

public class TokenIoServiceImpl implements TokenIoService {

  private final Key jwtKey;
  private final JwtParser jwtParser;

  public TokenIoServiceImpl(String jwtKeyBase64) {
    this.jwtKey = Keys.hmacShaKeyFor(
        Base64.getDecoder().decode(jwtKeyBase64)
    );
    this.jwtParser = Jwts.parserBuilder()
        .setSigningKey(this.jwtKey)
        .build();
  }

  @Override
  public String generateToken(String fiscalCode) {
    return Jwts.builder()
        .setSubject(fiscalCode)
        .setIssuedAt(Date.from(Instant.now()))
        .setExpiration(Date.from(Instant.now().plusSeconds(3600)))
        .signWith(this.jwtKey, SignatureAlgorithm.HS256)
        .compact();
  }

  @Override
  public IoUser getIoUser(String token) {
    final var claims = this.jwtParser.parseClaimsJws(token).getBody();
    return IoUser.builder().fiscalCode(claims.getSubject()).build();
  }
}
