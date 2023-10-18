package tt.observability.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tt.logging.CEFlogService;

public class AuthenticationException extends RuntimeException {

  private static final Logger logger = LogManager.getLogger(AuthenticationException.class);

  public AuthenticationException() {
    logger.warn("Authentication failed.");
    CEFlogService ceflogService = new CEFlogService();
    ceflogService.log("7701", "Authentication failed.", "7");
  }
}
