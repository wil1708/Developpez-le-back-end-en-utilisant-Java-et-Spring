package com.apiChatop.entities;

import jakarta.persistence.Entity;
import lombok.Data;

import java.io.Serializable;
@Data
@Entity
public class User extends BaseEntity implements Serializable {

    private String name;

    private String email;

    private String password;

}
