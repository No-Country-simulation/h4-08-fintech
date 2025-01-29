package com.web.backend.application.dtos.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicUserDto {

    private String username;
    private String email;

    public PublicUserDto(Builder build) {
        this.email = build.email;
        this.username = build.username;
    }

    public PublicUserDto() {

    }

    public static class Builder {
        private String username;

        private String email;

        public PublicUserDto.Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public PublicUserDto.Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public PublicUserDto build() {
            return new PublicUserDto(this);
        }
    }


}
