package com.web.backend.application.service.User;

import com.web.backend.application.DTO.PublicUserDto;
import com.web.backend.domain.model.user.UserModel;
import com.web.backend.domain.repository.User.RUser;
import com.web.backend.infrastructure.api.utils.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final RUser repository;

    public UserService(RUser repository) {
        this.repository = repository;
    }

    public PublicUserDto createUser(UserModel newUserModel) {
        try {
            Optional<UserModel> userExist = repository.findByEmail(newUserModel.getEmail());

            if (userExist.isPresent()) {
                throw new Exception("El usuairo existe");
            }

            String hashPass = BCrypt.hashpw(newUserModel.getPassword(), BCrypt.gensalt());


            UserModel createUserModel = new UserModel();
            newUserModel.setPassword(hashPass);
            newUserModel.setUsername(newUserModel.getUsername());
            newUserModel.setEmail(newUserModel.getEmail());

            PublicUserDto publicUserDto = new PublicUserDto.Builder().build();
            UserMapper.INSTANCE.toPublicUserFromEntity(newUserModel, publicUserDto);

            repository.save(newUserModel);

            return publicUserDto;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UserModel getUser(String email) {
        try {
            Optional<UserModel> userExist = repository.findByEmail(email);
            if (userExist.isEmpty()) {
                throw new Exception("El usuairo existe");
            } else {
                return userExist.get();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
