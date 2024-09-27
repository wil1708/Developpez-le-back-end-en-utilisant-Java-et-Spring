package com.apiChatop.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class RentalDto extends BaseEntityDto implements Serializable {

    private String name;

    private float surface;

    private double price;

    private String picture;

    private String description;

    private Long owner_id;

    private Date created_at;

    private Date updated_at;
}
