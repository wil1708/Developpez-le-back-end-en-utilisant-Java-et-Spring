package com.apiChatop.mapper;

import com.apiChatop.dtos.UserDto;
import com.apiChatop.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoMapper {
    DtoMapper INSTANCE = Mappers.getMapper(DtoMapper.class);

    @Mapping(source = "timestampedEntity.createdAt", target = "createdAt", dateFormat = "yyyy/MM/dd")
    @Mapping(source = "timestampedEntity.updatedAt", target = "updatedAt", dateFormat = "yyyy/MM/dd")
    UserDto userToUserDto(User user);
}
