package com.prometeo.drp_final.model.frontend;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDtoLogin {

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

    public UserDtoLogin(@NotEmpty String email, @NotEmpty String password) {
        this.email = email;
        this.password = password;
    }
}
