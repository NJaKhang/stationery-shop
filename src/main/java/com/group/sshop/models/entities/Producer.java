package com.group.sshop.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "producers")
@Getter
@Setter
public class Producer extends AbstractEntity{
    private String name;
    private String alias;

}
