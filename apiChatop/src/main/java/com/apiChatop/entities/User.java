package com.apiChatop.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class User extends BaseEntity implements Serializable {

    private String name;

    private String email;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private TimestampedEntity timestampedEntity;

}
