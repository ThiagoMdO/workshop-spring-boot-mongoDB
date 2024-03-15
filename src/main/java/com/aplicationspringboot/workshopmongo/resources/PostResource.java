package com.aplicationspringboot.workshopmongo.resources;

import com.aplicationspringboot.workshopmongo.dto.PostDTO;
import com.aplicationspringboot.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> findPostById(String id){
        PostDTO postDTO = postService.findPost(id);
        return ResponseEntity.ok().body(postDTO);
    }
}
