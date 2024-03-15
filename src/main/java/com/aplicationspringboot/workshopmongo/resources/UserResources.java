package com.aplicationspringboot.workshopmongo.resources;

import com.aplicationspringboot.workshopmongo.dto.UserDTO;
import com.aplicationspringboot.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        List<UserDTO> listDTO = userService.findAll();
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUsersById(@PathVariable("id") String id) {
        UserDTO userDTO = userService.findById(id);
        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createNewUser(@RequestBody UserDTO userDTORequest) {
        UserDTO userDTO = userService.create(userService.userDTOToUser(userDTORequest));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(userDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO){
        UserDTO userDTOResponse = userService.update(id, userDTO);
        return ResponseEntity.ok().body(userDTOResponse);
    }

}
