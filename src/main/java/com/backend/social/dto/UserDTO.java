package com.backend.social.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class UserDTO {

    @Id
    public String id;

    @NotNull(message = "name is missing")
    public String name;

    @NotNull(message = "password is missing")
    @Size(min = 8,message = "minimum password size is 8")
    public String password;

    @NotNull(message = "phone no missing")
    @Pattern(regexp = "[0-9]{10}")
    public String phone;

    @NotNull(message = "email is missing")
    @Email(message = "invalid email")
    public String email;

    @NotNull(message = "age is missing")
    @Min(value = 5,message = "minimum age is 5")
    @Max(value = 100,message = "maximum age is 100")
    public Integer age;

    @NotNull(message = "gender is missing")
    public String gender;

    @NotNull(message = "Address is missing")
    public String address;


}
