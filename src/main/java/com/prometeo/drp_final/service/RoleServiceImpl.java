package com.prometeo.drp_final.service;

import com.prometeo.drp_final.model.entity.Role;
import com.prometeo.drp_final.repository.RoleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;

    }
    @Override
    public Optional<Role> getByName(String name) {
        return roleRepository.findByName(name);
    }
}
