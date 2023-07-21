package it.pagopa.cstar.iomock.service;

import it.pagopa.cstar.iomock.dto.IoUser;

public interface TokenIoService {
  String generateToken(String fiscalCode);
  IoUser getIoUser(String token);
}
