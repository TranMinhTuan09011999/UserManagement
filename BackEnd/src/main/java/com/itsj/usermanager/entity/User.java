package com.itsj.usermanager.entity;

import com.itsj.usermanager.validation.email.ValidEmail;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USER")
public class User {

    @Id
    @NotBlank(message = "Username is required")
    @NotNull
    private String userName;

    @NotBlank(message = "Password is required")
    @NotNull
    private String password;

    @NotBlank(message = "Lastname is required")
    @NotNull
    private String lastname;

    @NotBlank(message = "First is required")
    @NotNull
    private String firstname;

    @NotBlank(message = "Email is required")
    @NotNull
    @ValidEmail
    private String email;
}
