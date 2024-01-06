package ecommerce.security.repositories;

import ecommerce.security.entities.Role;
import ecommerce.security.enums.RoleList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleList roleName);
}
