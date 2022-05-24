package com.backend.social.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CommentDTO {

    @NotBlank
    @Size(min=1 , message = "Comment is Empty")
    @Size(max=250, message = "Comment message size limit crossed")
    private String comment;

}
