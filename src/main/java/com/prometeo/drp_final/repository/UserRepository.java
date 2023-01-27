package com.prometeo.drp_final.repository;

import java.util.Optional;

import com.prometeo.drp_final.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);
  Optional<User> findById(Long id);
  Optional<User> findByConfirmationKey(String confirmationKey);

}
