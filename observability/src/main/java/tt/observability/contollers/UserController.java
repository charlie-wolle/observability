package tt.observability.contollers;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.CloseableThreadContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tt.observability.models.User;
import tt.observability.payloads.request.ChangeUserDataRequest;
import tt.observability.payloads.response.MessageResponse;
import tt.observability.services.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(value = "${allowedOrigin}", allowCredentials = "true")
public class UserController {

  private final UserService userService;

  @GetMapping("/userinfo")
  public ResponseEntity<User> getUserInfo() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();

    return ResponseEntity.ok().body(user);
  }

  @PutMapping("/updateself")
  public ResponseEntity<User> updateSelf(
      @Valid @RequestBody ChangeUserDataRequest changeUserDataRequest) {
    try (final CloseableThreadContext.Instance ignored =
        CloseableThreadContext.put("username", changeUserDataRequest.getUsername())) {
      User user =
          userService.updateSelf(
              changeUserDataRequest.getFirstname(), changeUserDataRequest.getLastname());

      return ResponseEntity.ok().body(user);
    }
  }

  @GetMapping("/all")
  public ResponseEntity<List<User>> all() {
    return ResponseEntity.ok().body(userService.getAllUsers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> one(@PathVariable Long id) {
    return ResponseEntity.ok().body(userService.getUserById(id));
  }

  @DeleteMapping("/{id}")
  ResponseEntity<MessageResponse> deleteUsers(@PathVariable Long id) {
    return ResponseEntity.ok().body(userService.deleteUserById(id));
  }
}
