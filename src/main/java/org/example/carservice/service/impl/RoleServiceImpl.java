package org.example.carservice.service.impl;

import org.example.carservice.model.Role;
import org.example.carservice.model.Role.RoleName;
import org.example.carservice.repository.RoleRepository;
import org.example.carservice.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private  final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getByName(String roleName) {
        return roleRepository.findFirstByRoleName(RoleName.valueOf(roleName));
    }
}
