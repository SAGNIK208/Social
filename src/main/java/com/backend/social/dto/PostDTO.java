package com.backend.social.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class PostDTO {

    @NotBlank
    @Size(min=1 , message = "Post is Empty")
    @Size(max=250, message = "Post message size limit crossed")
    public String post;

}
