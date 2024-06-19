package com.group.sshop.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category extends AbstractEntity {
    private String name;
    private String alias;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;
}
