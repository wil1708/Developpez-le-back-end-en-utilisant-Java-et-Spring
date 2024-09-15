package com.apiChatop.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class TimestampedEntity extends BaseEntity implements Serializable {

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
