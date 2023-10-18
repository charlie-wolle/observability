package tt.observability.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tt.observability.exceptions.RoleNotFoundException;
import tt.observability.models.ERole;
import tt.observability.models.Role;
import tt.observability.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

  private final RoleRepository roleRepository;

  public Role getRoleByName(ERole name) {
    return roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException(name));
  }

  public Role getRoleByLabel(String label) {
    return roleRepository
        .findByName(ERole.valueOf(label))
        .orElseThrow(() -> new RoleNotFoundException(label));
  }
}
