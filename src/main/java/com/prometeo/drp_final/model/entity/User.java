package com.prometeo.drp_final.model.entity;

import jakarta.persistence.*;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User extends BaseEntity implements UserDetails  {

  @Column(name = "companyName")
  private String companyName;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name="email",unique=true)
  private String email;

  @Column(name="email_confirmed")
  private Boolean emailConfirmed = Boolean.FALSE;

  @Column(name="confirmation_key")
  private String confirmationKey;

  @Column(name="password")
  private String password;

  @Column(name="wallet_pk")
  private String wallet_pk;


  @OneToOne
  @JoinColumn(name="role_id", referencedColumnName = "id")
  private Role role;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
              cascade = CascadeType.ALL)
  private Set<Device> devices = new HashSet<>();


  public void addDevice(Device device) {
    devices.add(device);
    device.setUser(this);
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> res = new ArrayList();
    res.add(new SimpleGrantedAuthority(role.getName()));
    return res;
  }


  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
