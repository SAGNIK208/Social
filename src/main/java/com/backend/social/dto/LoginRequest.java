package com.backend.social.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {
    @NotBlank
    @Email
    private String username;
    @NotBlank
    private String password;
}
