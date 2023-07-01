package org.example.carservice.repository;

import org.example.carservice.model.Role;
import org.example.carservice.model.Role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findFirstByRoleName(RoleName roleName);;
}
