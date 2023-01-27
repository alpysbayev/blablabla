package com.prometeo.drp_final.service;

import com.prometeo.drp_final.model.entity.Role;

import java.util.Optional;

public interface RoleService {

    public Optional<Role> getByName(String name);
}
