package com.prometeo.drp_final.service;

import com.prometeo.drp_final.model.entity.Role;
import com.prometeo.drp_final.model.entity.User;

import java.util.Optional;

public interface UserService {
    public Boolean toConfirmEmail(String confirmationKey);

    public Optional<User> getByEmail(String email);

    public Optional<User> getById(Long id);

    public void setRoleToUser(User user, Role role);

}
