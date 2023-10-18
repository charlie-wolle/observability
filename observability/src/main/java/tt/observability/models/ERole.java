package tt.observability.models;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ERole {
  ROLE_USER("user"),
  ROLE_MODERATOR("moderator"),
  ROLE_ADMIN("admin");

  public final String label;
}
