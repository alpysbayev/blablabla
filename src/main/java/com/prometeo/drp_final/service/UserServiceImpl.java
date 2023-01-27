package com.prometeo.drp_final.service;


import com.prometeo.drp_final.model.entity.Role;
import com.prometeo.drp_final.model.entity.Status;
import com.prometeo.drp_final.model.entity.User;
import com.prometeo.drp_final.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public Boolean toConfirmEmail(String confirmationKey){
        User user = userRepository.findByConfirmationKey(confirmationKey).get();
        if (user == null)
            return false;

        user.setEmailConfirmed(true);
        user.setUpdated(new Date());
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        return true;
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void setRoleToUser(User user, Role role) {
        user.setRole(role);
        userRepository.save(user);
    }
}
