package com.prometeo.drp_final.controller;

import com.prometeo.drp_final.service.UserService;
import com.prometeo.drp_final.utils.exception.InventException;
import com.prometeo.drp_final.utils.result.ResponseWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }


}
