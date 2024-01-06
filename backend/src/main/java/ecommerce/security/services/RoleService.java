package ecommerce.security.services;

import ecommerce.security.entities.Role;
import ecommerce.security.enums.RoleList;
import ecommerce.security.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> getByRoleName(RoleList roleName){
        return this.roleRepository.findByRoleName(roleName);
    }
}
