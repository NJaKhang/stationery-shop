package com.group.sshop.models.entities;

import com.group.sshop.models.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AbstractEntity {
    private String name;
    @Column(columnDefinition = "Text")
    private String description;
    @Column(unique = true)
    private String alias;
    @Column(unique = true)
    private String sku;
    private double price;
    private double discount;
    private double cost;
    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "thumbnail_id")
    private Image thumbnail;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<Image> images;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    @PrimaryKeyJoinColumn
    private ProductRecord record;

    public Double getDropPrice(){
        return price - discount;
    }

}
