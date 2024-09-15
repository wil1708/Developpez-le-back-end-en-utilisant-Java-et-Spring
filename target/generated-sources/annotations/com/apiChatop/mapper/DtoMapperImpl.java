package com.apiChatop.mapper;

import com.apiChatop.dtos.UserDto;
import com.apiChatop.entities.TimestampedEntity;
import com.apiChatop.entities.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-15T22:49:08+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
public class DtoMapperImpl implements DtoMapper {

    private final DateTimeFormatter dateTimeFormatter_yyyy_MM_dd_0102516032 = DateTimeFormatter.ofPattern( "yyyy/MM/dd" );

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        LocalDate createdAt = userTimestampedEntityCreatedAt( user );
        if ( createdAt != null ) {
            userDto.setCreatedAt( dateTimeFormatter_yyyy_MM_dd_0102516032.format( createdAt ) );
        }
        LocalDate updatedAt = userTimestampedEntityUpdatedAt( user );
        if ( updatedAt != null ) {
            userDto.setUpdatedAt( dateTimeFormatter_yyyy_MM_dd_0102516032.format( updatedAt ) );
        }
        userDto.setId( user.getId() );
        userDto.setVersion( user.getVersion() );
        userDto.setName( user.getName() );
        userDto.setEmail( user.getEmail() );

        return userDto;
    }

    private LocalDate userTimestampedEntityCreatedAt(User user) {
        if ( user == null ) {
            return null;
        }
        TimestampedEntity timestampedEntity = user.getTimestampedEntity();
        if ( timestampedEntity == null ) {
            return null;
        }
        LocalDate createdAt = timestampedEntity.getCreatedAt();
        if ( createdAt == null ) {
            return null;
        }
        return createdAt;
    }

    private LocalDate userTimestampedEntityUpdatedAt(User user) {
        if ( user == null ) {
            return null;
        }
        TimestampedEntity timestampedEntity = user.getTimestampedEntity();
        if ( timestampedEntity == null ) {
            return null;
        }
        LocalDate updatedAt = timestampedEntity.getUpdatedAt();
        if ( updatedAt == null ) {
            return null;
        }
        return updatedAt;
    }
}
