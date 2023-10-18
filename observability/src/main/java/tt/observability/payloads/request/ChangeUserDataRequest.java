package tt.observability.payloads.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangeUserDataRequest {
  @NotBlank private String username;
  @NotBlank private String firstname;
  @NotBlank private String lastname;
}
