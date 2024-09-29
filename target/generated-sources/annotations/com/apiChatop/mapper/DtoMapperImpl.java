package com.apiChatop.mapper;

import com.apiChatop.dtos.MessageDto;
import com.apiChatop.dtos.RentalDto;
import com.apiChatop.dtos.UserDto;
import com.apiChatop.entities.Message;
import com.apiChatop.entities.Rental;
import com.apiChatop.entities.TimestampedEntity;
import com.apiChatop.entities.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-29T18:52:55+0200",
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
            userDto.setCreated_at( dateTimeFormatter_yyyy_MM_dd_0102516032.format( createdAt ) );
        }
        LocalDate updatedAt = userTimestampedEntityUpdatedAt( user );
        if ( updatedAt != null ) {
            userDto.setUpdated_at( dateTimeFormatter_yyyy_MM_dd_0102516032.format( updatedAt ) );
        }
        userDto.setId( user.getId() );
        userDto.setVersion( user.getVersion() );
        userDto.setName( user.getName() );
        userDto.setEmail( user.getEmail() );

        return userDto;
    }

    @Override
    public RentalDto rentalToRentalDto(Rental rental) {
        if ( rental == null ) {
            return null;
        }

        RentalDto rentalDto = new RentalDto();

        LocalDate createdAt = rentalTimestampedEntityCreatedAt( rental );
        if ( createdAt != null ) {
            rentalDto.setCreated_at( Date.from( createdAt.atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        LocalDate updatedAt = rentalTimestampedEntityUpdatedAt( rental );
        if ( updatedAt != null ) {
            rentalDto.setUpdated_at( Date.from( updatedAt.atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        rentalDto.setOwner_id( rentalOwnerId( rental ) );
        rentalDto.setId( rental.getId() );
        rentalDto.setVersion( rental.getVersion() );
        rentalDto.setName( rental.getName() );
        rentalDto.setSurface( rental.getSurface() );
        rentalDto.setPrice( rental.getPrice() );
        rentalDto.setPicture( rental.getPicture() );
        rentalDto.setDescription( rental.getDescription() );

        return rentalDto;
    }

    @Override
    public Rental rentalDtoToRental(RentalDto rentalDto) {
        if ( rentalDto == null ) {
            return null;
        }

        Rental rental = new Rental();

        rental.setTimestampedEntity( rentalDtoToTimestampedEntity( rentalDto ) );
        rental.setOwner( rentalDtoToUser( rentalDto ) );
        rental.setId( rentalDto.getId() );
        rental.setVersion( rentalDto.getVersion() );
        rental.setName( rentalDto.getName() );
        rental.setSurface( rentalDto.getSurface() );
        rental.setPrice( rentalDto.getPrice() );
        rental.setPicture( rentalDto.getPicture() );
        rental.setDescription( rentalDto.getDescription() );

        return rental;
    }

    @Override
    public Message messageDtoToMessage(MessageDto messageDto) {
        if ( messageDto == null ) {
            return null;
        }

        Message message = new Message();

        message.setUser( messageDtoToUser( messageDto ) );
        message.setRental( messageDtoToRental( messageDto ) );
        message.setId( messageDto.getId() );
        message.setVersion( messageDto.getVersion() );
        message.setMessage( messageDto.getMessage() );

        return message;
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

    private LocalDate rentalTimestampedEntityCreatedAt(Rental rental) {
        if ( rental == null ) {
            return null;
        }
        TimestampedEntity timestampedEntity = rental.getTimestampedEntity();
        if ( timestampedEntity == null ) {
            return null;
        }
        LocalDate createdAt = timestampedEntity.getCreatedAt();
        if ( createdAt == null ) {
            return null;
        }
        return createdAt;
    }

    private LocalDate rentalTimestampedEntityUpdatedAt(Rental rental) {
        if ( rental == null ) {
            return null;
        }
        TimestampedEntity timestampedEntity = rental.getTimestampedEntity();
        if ( timestampedEntity == null ) {
            return null;
        }
        LocalDate updatedAt = timestampedEntity.getUpdatedAt();
        if ( updatedAt == null ) {
            return null;
        }
        return updatedAt;
    }

    private Long rentalOwnerId(Rental rental) {
        if ( rental == null ) {
            return null;
        }
        User owner = rental.getOwner();
        if ( owner == null ) {
            return null;
        }
        long id = owner.getId();
        return id;
    }

    protected TimestampedEntity rentalDtoToTimestampedEntity(RentalDto rentalDto) {
        if ( rentalDto == null ) {
            return null;
        }

        TimestampedEntity timestampedEntity = new TimestampedEntity();

        if ( rentalDto.getCreated_at() != null ) {
            timestampedEntity.setCreatedAt( LocalDateTime.ofInstant( rentalDto.getCreated_at().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }
        if ( rentalDto.getUpdated_at() != null ) {
            timestampedEntity.setUpdatedAt( LocalDateTime.ofInstant( rentalDto.getUpdated_at().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }

        return timestampedEntity;
    }

    protected User rentalDtoToUser(RentalDto rentalDto) {
        if ( rentalDto == null ) {
            return null;
        }

        User user = new User();

        if ( rentalDto.getOwner_id() != null ) {
            user.setId( rentalDto.getOwner_id() );
        }

        return user;
    }

    protected User messageDtoToUser(MessageDto messageDto) {
        if ( messageDto == null ) {
            return null;
        }

        User user = new User();

        if ( messageDto.getUser_id() != null ) {
            user.setId( messageDto.getUser_id() );
        }

        return user;
    }

    protected Rental messageDtoToRental(MessageDto messageDto) {
        if ( messageDto == null ) {
            return null;
        }

        Rental rental = new Rental();

        if ( messageDto.getRental_id() != null ) {
            rental.setId( messageDto.getRental_id() );
        }

        return rental;
    }
}
