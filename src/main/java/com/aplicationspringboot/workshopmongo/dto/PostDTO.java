package com.aplicationspringboot.workshopmongo.dto;

import com.aplicationspringboot.workshopmongo.domain.Post;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private Date date;

    private String title;

    private String body;

    private AuthorDTO author;

    private List<CommentDTO> comment = new ArrayList<>();

    public PostDTO(){
    }

    public PostDTO(Post post) {
        this.id = post.getId();
        this.date = post.getDate();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.author = post.getAuthor();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public List<CommentDTO> getComment() {
        return comment;
    }

    public void setComment(List<CommentDTO> comment) {
        this.comment = comment;
    }
}
