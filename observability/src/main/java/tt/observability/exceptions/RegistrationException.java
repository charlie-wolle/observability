package tt.observability.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tt.logging.CEFlogService;

public class RegistrationException extends RuntimeException {

  private static final Logger logger = LogManager.getLogger(RegistrationException.class);

  public RegistrationException(String message) {
    logger.warn("Registration failed: {}", message);
    CEFlogService ceflogService = new CEFlogService();
    ceflogService.log("7804", "Registration failed: " + message, "8");
  }
}
