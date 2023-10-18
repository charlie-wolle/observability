package tt.observability.services;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tt.logging.CEFlogService;
import tt.observability.exceptions.RegistrationException;
import tt.observability.models.ERole;
import tt.observability.models.Role;
import tt.observability.models.User;
import tt.observability.payloads.request.RegisterRequest;
import tt.observability.repositories.UserRepository;
import tt.observability.security.jwt.JwtUtils;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;
  private final UserRepository userRepository;
  private final RoleService roleService;
  private final PasswordEncoder encoder;
  private final CEFlogService ceflogService;

  private static final Logger logger = LogManager.getLogger(AuthService.class);

  public ResponseCookie login(String username, String password) {

    Authentication authentication;

    authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = jwtUtils.generateJwtToken(authentication);

    logger.info("Authentication successful");
    ceflogService.log("1201", "Authentication successful.", "2");

    return ResponseCookie.from("jwtToken", jwt).httpOnly(false).secure(true).path("/").build();
  }

  public User register(RegisterRequest registerRequest) {
    if (Boolean.TRUE.equals(userRepository.existsByUsername(registerRequest.getUsername()))) {
      throw new RegistrationException("Username already taken.");
    }

    if (Boolean.TRUE.equals(userRepository.existsByEmail(registerRequest.getEmail()))) {
      throw new RegistrationException("Email already taken.");
    }

    Set<String> strRoles = registerRequest.getRoles();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleService.getRoleByName(ERole.ROLE_USER);

      roles.add(userRole);
    } else {
      roles = strRoles.stream().map(roleService::getRoleByLabel).collect(Collectors.toSet());
    }

    // Create new user's account
    User user =
        User.builder()
            .username(registerRequest.getUsername())
            .email(registerRequest.getEmail())
            .password(encoder.encode(registerRequest.getPassword()))
            .firstname(registerRequest.getFirstname())
            .lastname(registerRequest.getLastname())
            .roles(roles)
            .build();

    userRepository.save(user);
    logger.info("Registration successful.");
    ceflogService.log("4501", "Registration successful.", "5");

    return user;
  }

  public ResponseCookie logout() {
    logger.info("Logout successful.");
    ceflogService.log("1202", "Logout successful.", "2");
    return jwtUtils.getCleanJwtCookie();
  }
}
