package com.apiChatop.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class MessageDto extends BaseEntityDto implements Serializable {

    private String message;

    private Long user_id;

    private Long rental_id;
}
