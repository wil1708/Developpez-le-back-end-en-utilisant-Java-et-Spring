package com.apiChatop.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Message extends BaseEntity implements Serializable {

    @Column(columnDefinition = "TEXT")
    private String message;

    @ManyToOne
    private User user;

    @ManyToOne
    private Rental rental;
}
