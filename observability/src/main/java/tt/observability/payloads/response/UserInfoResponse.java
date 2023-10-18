package tt.observability.payloads.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoResponse {
  private Long id;
  private String username;
  private String firstname;
  private String lastname;
  private String email;
  private List<String> roles;
}
