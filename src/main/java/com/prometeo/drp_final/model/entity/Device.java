package com.prometeo.drp_final.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name ="devices")
public class Device extends BaseEntity {

    @Column(name = "imei")
    private String imei;
    @Column(name = "model")
    private String model;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "comission")
    private Integer comission;
    @Column(name = "public_key")
    private String public_key;

//    @OneToMany(mappedBy = "device", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private Set<SmartContract> smartContracts = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "devices_smart_contracts_",
            joinColumns = @JoinColumn(name = "device_id"),
            inverseJoinColumns = @JoinColumn(name = "smart_contract_id"))
    private Set<SmartContract> smartContracts = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;


//    public void addSmartContract(SmartContract smartContract) {
//        smartContracts.add(smartContract);
//        smartContract.setDevice(this);
//    }

}
