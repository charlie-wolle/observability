package tt.observability.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tt.logging.CEFlogService;
import tt.observability.models.ERole;

public class RoleNotFoundException extends RuntimeException {

  private static final Logger logger = LogManager.getLogger(RoleNotFoundException.class);

  public RoleNotFoundException(ERole name) {
    logger.warn("Could not find role by name: {}", name);
    CEFlogService ceflogService = new CEFlogService();
    ceflogService.log("4608", "Could not find role by name: " + name, "6");
  }

  public RoleNotFoundException(String label) {
    logger.warn("Could not find role by label: {}", label);
    CEFlogService ceflogService = new CEFlogService();
    ceflogService.log("4608", "Could not find role by label: " + label, "6");
  }
}
