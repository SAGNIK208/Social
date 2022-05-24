package com.backend.social.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Document(collection = "comments")
public class CommentEntity {

    @Id
    private String id;

    @NotNull
    private String comment;

    @NotNull
    private String postId;

    @NotNull
    private String userId;
}
