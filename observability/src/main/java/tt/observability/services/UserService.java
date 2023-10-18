package tt.observability.services;

import java.util.List;
import tt.logging.CEFlogService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tt.observability.exceptions.UserNotFoundException;
import tt.observability.models.User;
import tt.observability.payloads.response.MessageResponse;
import tt.observability.repositories.UserRepository;
import tt.observability.security.jwt.JwtUtils;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final JwtUtils jwtUtils;
  private final CEFlogService ceflogService;

  private static final Logger logger = LogManager.getLogger(UserService.class);

  // API + CRUD ------------------------------------------------------------------------------->
  public User updateSelf(String firstname, String lastname) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    User user = (User) authentication.getPrincipal();

    user.setFirstname(firstname);
    user.setLastname(lastname);

    userRepository.save(user);

    logger.info("Update successful.");
    ceflogService.log("1303", "Update successful.", "3");
    return user;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public MessageResponse deleteUserById(Long id) {
    userRepository.deleteById(id);

    logger.info("Deletion successful.");
    ceflogService.log("4502", "Deletion successful.", "5");
    return new MessageResponse("User " + id + ": Deletion successful.");
  }

  public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
  }

  // Helper ---------------------------------------------------------------------------------->
  @Override
  @Transactional
  public User loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository
        .findByUsername(username)
        .orElseThrow(UserNotFoundException::new);
  }

  @Transactional
  public User getUserByCookie(ResponseCookie jwtCookie) {
    String username = jwtUtils.getUserNameFromJwtToken(jwtCookie.getValue());
    return loadUserByUsername(username);
  }
}
