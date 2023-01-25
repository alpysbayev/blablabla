package com.prometeo.drp_final.service;


import com.prometeo.drp_final.model.entity.Status;
import com.prometeo.drp_final.model.entity.User;
import com.prometeo.drp_final.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Repository
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
}
