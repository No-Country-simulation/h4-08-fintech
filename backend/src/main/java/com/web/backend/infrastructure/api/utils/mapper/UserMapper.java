package com.web.backend.infrastructure.api.utils.mapper;

import com.web.backend.application.dto.user.PublicUserDto;
import com.web.backend.application.dto.UserUpdateDto;
import com.web.backend.domain.model.user.UserModel;
import com.web.backend.infrastructure.api.controller.auth.RegisterAuthRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    void updateUserFromDto(UserUpdateDto source, @MappingTarget UserModel target);
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    void toPublicUserFromEntity(UserModel source, @MappingTarget PublicUserDto target);
    void RegisterUserToUserModel(RegisterAuthRequest source,@MappingTarget UserModel target);
}
