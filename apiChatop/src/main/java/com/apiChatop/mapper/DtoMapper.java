package com.apiChatop.mapper;

import com.apiChatop.dtos.MessageDto;
import com.apiChatop.dtos.RentalDto;
import com.apiChatop.dtos.UserDto;
import com.apiChatop.entities.Message;
import com.apiChatop.entities.Rental;
import com.apiChatop.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoMapper {
    DtoMapper INSTANCE = Mappers.getMapper(DtoMapper.class);

    @Mapping(source = "timestampedEntity.createdAt", target = "created_at", dateFormat = "yyyy/MM/dd")
    @Mapping(source = "timestampedEntity.updatedAt", target = "updated_at", dateFormat = "yyyy/MM/dd")
    UserDto userToUserDto(User user);

    @Mapping(source = "timestampedEntity.createdAt", target = "created_at")
    @Mapping(source = "timestampedEntity.updatedAt", target = "updated_at")
    @Mapping(source = "owner.id", target = "owner_id")
    RentalDto rentalToRentalDto(Rental rental);

    @Mapping(source = "created_at", target = "timestampedEntity.createdAt")
    @Mapping(source = "updated_at", target = "timestampedEntity.updatedAt")
    @Mapping(source = "owner_id", target = "owner.id")
    Rental rentalDtoToRental(RentalDto rentalDto);

    @Mapping(source = "user_id", target = "user.id")
    @Mapping(source = "rental_id", target = "rental.id")
    Message messageDtoToMessage(MessageDto messageDto);
}
