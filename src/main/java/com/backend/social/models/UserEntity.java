package com.backend.social.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class UserEntity {

    @Id
    public String id;

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
