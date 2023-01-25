package com.prometeo.drp_final.model.frontend;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    public static final long serialVersionUID= 12L;

    private Long id; //ID для базы данных
    @NotEmpty
    private String companyName;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

    public UserDto() {
    }

    public UserDto(String companyName, String password, String firstName, String lastName, String email) {
        this.companyName = companyName;
        this.password = password;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
