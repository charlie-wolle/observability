package tt.observability.advices;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tt.logging.CEFlogService;
import tt.observability.exceptions.AuthenticationException;
import tt.observability.exceptions.RegistrationException;
import tt.observability.exceptions.RoleNotFoundException;
import tt.observability.exceptions.UserNotFoundException;

@ControllerAdvice
@RequiredArgsConstructor
class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  private final CEFlogService ceflogService;

  private static final Logger log4j2 =
      LogManager.getLogger(RestResponseEntityExceptionHandler.class);

  @ResponseBody
  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String userNotFoundHandler(UserNotFoundException ex) {
    return ex.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(RoleNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String userNotFoundHandler(RoleNotFoundException ex) {
    return ex.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(AuthenticationException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  void authenticationFailedHandier(AuthenticationException ex) {
    log4j2.warn("Authentication failed.");
    ceflogService.log("7701", "Authentication failed.", "7");
  }

  @ResponseBody
  @ExceptionHandler(BadCredentialsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  void credentialsTakenHandler(BadCredentialsException ex) {
    log4j2.warn("Authentication failed: {}", ex.getMessage());
    ceflogService.log("7804", "Authentication failed: " + ex.getMessage(), "8");
  }

  @ResponseBody
  @ExceptionHandler(RegistrationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String registrationFaildHandler(RegistrationException ex) {
    return ex.getMessage();
  }
}
