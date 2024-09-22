package com.apiChatop.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Rental extends BaseEntity implements Serializable {

    private String name;

    private float surface;

    private double price;

    private String picture;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    private User owner;

    @OneToOne(cascade = CascadeType.ALL)
    private TimestampedEntity timestampedEntity;
}
