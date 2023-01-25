package com.prometeo.drp_final.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name ="smart_contracts")
public class SmartContract extends BaseEntity {
    @Column(name="type")
    private String type;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "device_id")
//    private Device device;

//    @ManyToMany
//    @JoinTable(name = "smart_contracts_devices",
//            joinColumns = @JoinColumn(name = "smart_contract_id"),
//            inverseJoinColumns = @JoinColumn(name = "device_id"))
//    private Set<Device> device = new HashSet<>();
}
