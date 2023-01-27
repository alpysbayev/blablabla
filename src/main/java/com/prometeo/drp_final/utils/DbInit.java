package com.prometeo.drp_final.utils;


import com.prometeo.drp_final.model.entity.Permission;
import com.prometeo.drp_final.model.entity.Role;
import com.prometeo.drp_final.model.entity.Status;
import com.prometeo.drp_final.model.entity.User;
import com.prometeo.drp_final.repository.PermissionRepository;
import com.prometeo.drp_final.repository.RoleRepository;
import com.prometeo.drp_final.repository.UserRepository;
import com.prometeo.drp_final.utils.exception.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Component
@Transactional
public class DbInit {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Value("${defaultaccount.username}")
    private String email;

    @Value("${defaultaccount.password}")
    private String password;


    @PostConstruct
    private void postConstruct() throws ExecutionException, InterruptedException {


        //создание справочника ролей
        if (roleRepository.findAll().isEmpty()) {
            try {

                for (String name : Arrays.asList("SUPERADMIN", "ADMIN", "USER1", "USER2")) {
                    Role role = new Role();
                    role.setName(name);role.setStatus(Status.ACTIVE);role.setCreated(new Date());role.setUpdated(new Date());
                    roleRepository.save(role);
                }

            } catch (Exception e) {
                System.out.println("DbInit -> postconstruct -> creating role dictionary : " + e.getMessage());
                return;
            }
        }
        //создание справочника прав
        if (permissionRepository.findAll().isEmpty()) {
            try {
                for (String name : Set.of("LAYER0", "LAYER1", "LAYER2")) {
                    Permission perm = new Permission();
                    perm.setName(name);perm.setStatus(Status.ACTIVE);perm.setCreated(new Date());perm.setUpdated(new Date());
                    if (name.equals("LAYER0"))
                        roleRepository.findByName("ADMIN").map(role ->{perm.setRole(role);
                        return permissionRepository.save(perm);}).orElseThrow(() -> new ResourceNotFoundException("Not found Role with name ADMIN" ));
                    if (name.equals("LAYER1"))
                        roleRepository.findByName("USER1").map(role ->{perm.setRole(role);
                        return permissionRepository.save(perm);}).orElseThrow(() -> new ResourceNotFoundException("Not found Role with name USER1" ));
                    if (name.equals("LAYER2"))
                        roleRepository.findByName("USER2").map(role ->{perm.setRole(role);
                        return permissionRepository.save(perm);}).orElseThrow(() -> new ResourceNotFoundException("Not found Role with name USER2" ));
                }
            } catch (Exception e) {
                System.out.println("DbInit -> postconstruct -> creating permission dictionary : " + e.getMessage());
                return;
            }
        }

        // создание юзера при инициализации базы данных
        try {
            if (userRepository.findByEmail(email).isEmpty()){
                User user = new User();
                user.setCompanyName("Prometeo LLC");user.setFirstName("Admin");user.setLastName("Adminov");
                user.setEmail(email);user.setEmailConfirmed(true);user.setConfirmationKey("AAAA");
                user.setPassword(passwordEncoder.encode(password));user.setWallet_pk("Wallet_PK");
                user.setStatus(Status.ACTIVE);user.setCreated(new Date());user.setUpdated(new Date());
                user.setRole(roleRepository.findByName("SUPERADMIN").get());
                userRepository.save(user);

            }

        }catch (Exception e){
            System.out.println("DbInit -> postconstruct -> creating superadmin : " + e.getMessage());
            return;
        }

    }


}
