package com.prometeo.drp_final.controller;

import com.prometeo.drp_final.model.entity.Role;
import com.prometeo.drp_final.model.entity.User;
import com.prometeo.drp_final.model.frontend.SetRoleDto;
import com.prometeo.drp_final.service.RoleService;
import com.prometeo.drp_final.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
public class RoleController {

    private final UserService userService;
    private final RoleService roleService;

    public RoleController(UserService userService, RoleService roleService) {

        this.userService = userService;
        this.roleService = roleService;
    }

    @PutMapping("/users/roles")
    public void setRole(@RequestBody SetRoleDto dto) {
        Optional<User> userExistence = userService.getByEmail(dto.getEmail());
        Optional<Role> roleExistence = roleService.getByName(dto.getRole());

        if(userExistence.isPresent() && roleExistence.isPresent()) {
            User user = userExistence.get();
            Role role = roleExistence.get();

            userService.setRoleToUser(user, role);
        }
    }

}
