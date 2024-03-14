package com.aplicationspringboot.workshopmongo.resources;

import com.aplicationspringboot.workshopmongo.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResources {


    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(){
        User us1 = new User(1L, "Maria", "maria@gdsf.coim");
        User us2 = new User(2L, "Mariano", "mariaNO@gdsf.coim");
        List<User> users = Arrays.asList(us1, us2);

        return ResponseEntity.ok().body(users);
    }
}
