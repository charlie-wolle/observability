package tt.observability.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tt.logging.CEFlogService;

public class UserNotFoundException extends RuntimeException {
  private static final Logger logger = LogManager.getLogger(UserNotFoundException.class);

  public UserNotFoundException(Long id) {
    logger.warn("Could not find user by id: {}", id);
    CEFlogService ceflogService = new CEFlogService();
    ceflogService.log("4608", "Could not find user by id: " + id, "6");
  }

  public UserNotFoundException() {
    logger.warn("Could not find user by username.");
    CEFlogService ceflogService = new CEFlogService();
    ceflogService.log("4608", "Could not find user by username.", "6");
  }
}
