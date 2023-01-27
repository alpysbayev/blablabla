package com.prometeo.drp_final.controller;

import com.prometeo.drp_final.model.entity.Role;
import com.prometeo.drp_final.model.entity.User;
import com.prometeo.drp_final.model.frontend.SetRoleDto;
import com.prometeo.drp_final.model.frontend.UserDto;
import com.prometeo.drp_final.service.RoleService;
import com.prometeo.drp_final.service.UserService;
import com.prometeo.drp_final.utils.exception.InventException;
import com.prometeo.drp_final.utils.result.ResponseWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {

        this.userService = userService;
        this.roleService = roleService;
    }


//    @GetMapping("admin/users")
//    public List<UserDto> getUsers() {
//
//    }

}
