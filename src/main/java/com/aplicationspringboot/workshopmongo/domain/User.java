package com.aplicationspringboot.workshopmongo.domain;

import com.aplicationspringboot.workshopmongo.dto.UserDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "user")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String name;

    private String email;

    //No spring data, para dizer que um atributo esta referenciando outra coleção do mongoDB, se utiliza @DBRef
    //vai ser apenas carregado se acessalos, usar o lazy = true
    @DBRef(lazy = true)
    private List<Post> posts = new ArrayList<>();

    public User() {

    }

    public User(String id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User(UserDTO userDTO){
        this.id = userDTO.getId();
        this.name = userDTO.getName();
        this.email = userDTO.getId();
        this.posts = userDTO.getPosts();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
