package com.prometeo.drp_final.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.repository.cdi.Eager;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name ="roles")
public class Role extends BaseEntity{

    @Column(name="name")
    private String name;

    @OneToOne(mappedBy = "role")
    private User user;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    Set<Permission> permissions = new HashSet<>();


    public String getName() {
        return name;
    }

    public void addPermission(Permission permission) {
        permissions.add(permission);
        permission.setRole(this);
    }

    public void removePermission(Permission permission) {

        permissions.remove(permission);

    }
}
