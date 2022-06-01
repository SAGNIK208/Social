package com.backend.social.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @NotNull
    public String name;

    @NotNull
    public String password;

    @NotNull
    public String phone;

    @NotNull
    public String email;

    @NotNull
    public Integer age;

    @NotNull
    public String gender;

    @NotNull
    public String address;

}
