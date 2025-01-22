package com.web.backend.application.service.User;

import com.web.backend.application.DTO.Auth.LoginDto;
import com.web.backend.application.DTO.User.PublicUserDto;
import com.web.backend.domain.model.user.UserModel;
import com.web.backend.domain.repository.User.RUser;
import com.web.backend.infrastructure.api.utils.auth.LoginType;
import com.web.backend.infrastructure.api.utils.mapper.UserMapper;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final RUser repository;
    private final EntityManager entityManager;

    public PublicUserDto loginUser(UserModel loginData, LoginType loginType) {
        try (Session session = entityManager.unwrap(Session.class)) {
            Filter filter = session.enableFilter("deletedUserFilter");
            filter.setParameter("isDeleted", false);

            UserModel userEntity;
            if (!loginData.getEmail().isEmpty()) {
                userEntity = repository.findByEmail(loginData.getEmail())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "No se encontró el usuario con ese email"
                        ));
            } else {
                userEntity = repository.findByUsername(loginData.getUsername())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "No se encontró el usuario con ese nombre de usuario"
                        ));
            }
            if (loginType != LoginType.GOOGLE && (loginData.getPassword() == null || loginData.getPassword().isEmpty())) {
                throw new RuntimeException("Password cannot be null or empty");
            }

            System.out.println("loginuser antes mapper " + userEntity.getEmail() + userEntity.getUsername());
            PublicUserDto publicUserDto = new PublicUserDto();

            UserMapper mapper = UserMapper.INSTANCE;

            mapper.toPublicUserFromEntity(userEntity, publicUserDto);
            UserMapper.INSTANCE.toPublicUserFromEntity(userEntity,publicUserDto);
            System.out.println("LOGIN USER "+publicUserDto.getEmail());
            return publicUserDto;
        }
    }

    public PublicUserDto createUser(UserModel newUserModel, LoginType loginType) {


        try (Session session = entityManager.unwrap(Session.class)) {


            if (repository.findByEmail(newUserModel.getEmail()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario ya existe");
            }

            newUserModel.setDeleted(false);

            Filter filter = session.enableFilter("deletedUserFilter");
            filter.setParameter("isDeleted", false);

            if (loginType == LoginType.PASSWORD) {
                String hashedPassword = BCrypt.hashpw(newUserModel.getPassword(), BCrypt.gensalt());
                newUserModel.setPassword(hashedPassword);
            }

            repository.save(newUserModel);

            session.disableFilter("deletedUserFilter");

            PublicUserDto publicUserDto = new PublicUserDto.Builder().build();

            UserMapper mapper = UserMapper.INSTANCE;

            mapper.toPublicUserFromEntity(newUserModel, publicUserDto);

            return publicUserDto;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el usuario: " + e.getMessage(), e);
        }
    }


    public PublicUserDto updateUser(UserModel updatedUser) {
        UserModel user = repository.findById(updatedUser.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        if (updatedUser.getPassword() != null) {
            String hashedPassword = BCrypt.hashpw(updatedUser.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);
        }
        user.setUsername(updatedUser.getUsername());

        repository.save(user);

        PublicUserDto publicUserDto = new PublicUserDto.Builder().build();

        UserMapper mapper = UserMapper.INSTANCE;

        mapper.toPublicUserFromEntity(user, publicUserDto);

        return publicUserDto;
    }


    public List<UserModel> findAllUsers(boolean includeDeleted) {
        try (Session session = entityManager.unwrap(Session.class)) {
            Filter filter = session.enableFilter("deletedUserFilter");
            filter.setParameter("isDeleted", includeDeleted);

            List<UserModel> users = repository.findAll();

            session.disableFilter("deletedUserFilter");
            return users;
        }
    }


    public UserModel getDetailUser(String email) {
        try (Session session = entityManager.unwrap(Session.class)) {
            Filter filter = session.enableFilter("deletedUserFilter");
            filter.setParameter("isDeleted", false);

            return repository.findByEmail(email)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe"));
        }
    }

}
