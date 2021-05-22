package main.entity;

import lombok.*;

import main.controller.Role;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class User {
    String login;
    String password;
    Role role;
}
