package com.example.myjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class User {

        private String login;
        private String password;
        private String firstName;
        private String lastName;
        private Set<Role> roles;

        @Override
        public String toString() {
            return "User{" +
                    "login='" + login + '\'' +
                    ", password='" + password + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", roles=" + roles +
                    '}';
        }
    }

