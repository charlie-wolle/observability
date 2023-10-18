package tt.observability.contollers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.CloseableThreadContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tt.observability.models.User;
import tt.observability.payloads.request.LoginRequest;
import tt.observability.payloads.request.RegisterRequest;
import tt.observability.payloads.response.MessageResponse;
import tt.observability.payloads.response.UserInfoResponse;
import tt.observability.services.AuthService;
import tt.observability.services.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(value = "${allowedOrigin}", allowCredentials = "true")
public class AuthController {
  private final UserService userService;
  private final AuthService authService;

  private static final Logger logger = LogManager.getLogger(AuthController.class);
  private static final String USERNAME_PROP = "username";

  @PostMapping("/login")
  public ResponseEntity<UserInfoResponse> authenticateUser(
      @Valid @RequestBody LoginRequest loginRequest) {

    try (final CloseableThreadContext.Instance ignored =
        CloseableThreadContext.put(USERNAME_PROP, loginRequest.getUsername())) {

      ResponseCookie jwtCookie =
          authService.login(loginRequest.getUsername(), loginRequest.getPassword());

      User user = userService.getUserByCookie(jwtCookie);

      return ResponseEntity.ok()
          .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
          .body(
              UserInfoResponse.builder()
                  .id(user.getId())
                  .username(user.getUsername())
                  .firstname(user.getFirstname())
                  .lastname(user.getLastname())
                  .email(user.getEmail())
                  .roles(user.getRoles().stream().map(role -> role.getName().name()).toList())
                  .build());
    }
  }

  @PostMapping("/register")
  public ResponseEntity<UserInfoResponse> registerUser(
      @Valid @RequestBody RegisterRequest registerRequest) {

    try (final CloseableThreadContext.Instance ignored =
        CloseableThreadContext.put(USERNAME_PROP, registerRequest.getUsername())) {

      User user = authService.register(registerRequest);

      return ResponseEntity.ok(
          new UserInfoResponse(
              user.getId(),
              user.getUsername(),
              user.getFirstname(),
              user.getLastname(),
              user.getEmail(),
              user.getRoles().stream().map(role -> role.getName().toString()).toList()));
    }
  }

  @PostMapping("/logout")
  public ResponseEntity<MessageResponse> logoutUser() {
    try {
      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      try (final CloseableThreadContext.Instance ignored =
          CloseableThreadContext.put(USERNAME_PROP, user.getUsername())) {
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, authService.logout().toString())
            .body(new MessageResponse("You've been signed out!"));
      }
    } catch (Exception e) {
      logger.info("No user logged in.");
      return ResponseEntity.badRequest().body(new MessageResponse("No user logged in."));
    }
  }
}
