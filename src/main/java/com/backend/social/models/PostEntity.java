package com.backend.social.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "posts")
public class PostEntity {

    @Id
    private String id;

    @NotNull
    private String post;

    @NotNull
    private String userId;

    private List<CommentEntity> comments = new ArrayList<>();

    public void addComment(CommentEntity comment){
        comments.add(comment);
    }

    public void deleteComment(CommentEntity comment){
        comments.remove(comment);
    }

    private  int getIndex(CommentEntity commentEntity){
        for(int i=0;i<comments.size();i++){
            if(comments.get(i).getId().equals(commentEntity.getId())){
                return i;
            }
        }
        return -1;
    }
    public  void updateComment(CommentEntity updatedComment){
        int index = getIndex(updatedComment);
        comments.set(index,updatedComment);
    }

}
