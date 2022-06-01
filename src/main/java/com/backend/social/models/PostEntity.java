package com.backend.social.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String post;

    @NotNull
    private Integer userId;

    @OneToMany(
            mappedBy = "postId",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
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
