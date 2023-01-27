package com.prometeo.drp_final.model.frontend;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SetRoleDto {

    @NotEmpty
    private String email;
    @NotEmpty
    private String role;

    public SetRoleDto(@NotEmpty String email, @NotEmpty String role) {
        this.email = email;
        this.role = role;
    }
}
