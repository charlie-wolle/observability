package tt.observability.payloads.request;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;
import lombok.Data;

@Data
public class RegisterRequest {
  @NotBlank private String username;
  @NotBlank private String password;
  @NotBlank private String email;
  private String firstname;
  private String lastname;
  private Set<String> roles;
}
