package com.group.sshop.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductRecord {
    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id")
    private Product track;

    private int sale;
    private int stock;
}
