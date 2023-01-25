package com.prometeo.drp_final.repository;

import com.prometeo.drp_final.model.entity.Role;
import com.prometeo.drp_final.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
