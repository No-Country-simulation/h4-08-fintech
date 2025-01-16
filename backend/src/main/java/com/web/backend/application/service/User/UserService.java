package com.web.backend.application.service.User;

import com.web.backend.application.DTO.Auth.LoginDto;
import com.web.backend.application.DTO.User.PublicUserDto;
import com.web.backend.config.filter.SpecificationFilter;
import com.web.backend.domain.model.user.UserModel;
import com.web.backend.domain.repository.User.RUser;
import com.web.backend.infrastructure.api.utils.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final RUser repository;

    public PublicUserDto createUser(UserModel newUserModel) {
        try {

            Specification<UserModel> spec = Specification
                    .where(SpecificationFilter.<UserModel>isNotDeleted())
                    .and(SpecificationFilter.fieldEquals("email", newUserModel.getEmail()));

            Optional<UserModel> userExist = repository.findOne(spec);

            if (userExist.isPresent()) {
                throw new ResponseStatusException(HttpStatus.CREATED, "El usuairo existe");
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

    public PublicUserDto delete(LoginDto userData) {

        try {

            Specification<UserModel> spec = Specification
                    .where(SpecificationFilter.<UserModel>isNotDeleted())
                    .and(SpecificationFilter.fieldEquals("email", userData.getEmail()));

            Optional<UserModel> userExist = repository.findOne(spec);

            if (userExist.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario con el email " + userData.getEmail());

            userExist.get().setDeleted(true);

            repository.save(userExist.get());

            PublicUserDto publicUserDto = new PublicUserDto.Builder().build();
            UserMapper.INSTANCE.toPublicUserFromEntity(userExist.get(), publicUserDto);
            return publicUserDto;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

    public PublicUserDto updateUser(UserModel newUserData) {

        try {

            Specification<UserModel> spec = Specification
                    .where(SpecificationFilter.<UserModel>isNotDeleted())
                    .and(SpecificationFilter.fieldEquals("email", newUserData.getEmail()));

            Optional<UserModel> userExist = repository.findOne(spec);

            if (userExist.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario con el email " + newUserData.getEmail());

            String hashPass = BCrypt.hashpw(newUserData.getPassword(), BCrypt.gensalt());

            userExist.get().setUsername(newUserData.getUsername());
            userExist.get().setPassword(hashPass);

            repository.save(userExist.get());

            PublicUserDto publicUserDto = new PublicUserDto.Builder().build();
            UserMapper.INSTANCE.toPublicUserFromEntity(userExist.get(), publicUserDto);
            return publicUserDto;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

    public PublicUserDto loginUser(LoginDto loginData) {
        UserModel userEntity;
        if (!loginData.getEmail().isEmpty()) {
            userEntity = repository.findByEmail(loginData.getEmail())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "No se encontró el usuario con ese email"
                    ));
        } else {
            Specification<UserModel> spec = Specification
                    .where(SpecificationFilter.<UserModel>isNotDeleted())
                    .and(SpecificationFilter.fieldEquals("username", loginData.getUsername()));

            userEntity = repository.findOne(spec)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "No se encontró el usuario con ese nombre de usuario"
                    ));
        }

        if (!BCrypt.checkpw(loginData.getPassword(), userEntity.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        PublicUserDto publicUserDto = new PublicUserDto.Builder().build();
        UserMapper.INSTANCE.toPublicUserFromEntity(userEntity, publicUserDto);
        return publicUserDto;

    }


    public UserModel getDetailUser(String email) {
        try {

            Specification<UserModel> spec = Specification
                    .where(SpecificationFilter.<UserModel>isNotDeleted())
                    .and(SpecificationFilter.fieldEquals("email", email));


            Optional<UserModel> userExist = repository.findOne(spec);
            if (userExist.isEmpty()) {
                throw new Exception("El usuairo no existe");
            } else {
                return userExist.get();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
