package com.aplicationspringboot.workshopmongo.resources;

import com.aplicationspringboot.workshopmongo.dto.PostDTO;
import com.aplicationspringboot.workshopmongo.resources.util.URL;
import com.aplicationspringboot.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> findPostById(@PathVariable String id){
        PostDTO postDTO = postService.findPost(id);
        return ResponseEntity.ok().body(postDTO);
    }

//    @GetMapping("/{title}")
//    public ResponseEntity<List<PostDTO>> findPostByTitle(@PathVariable("title") String title){
//        return ResponseEntity.ok().body(postService.findPostsByTitle(title));
//    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<PostDTO>> findPostByTitle(@RequestParam(value = "text", defaultValue = "") String text){
        text = URL.decodeParam(text);
        List<PostDTO> list = postService.findPostsByTitle(text);
        return ResponseEntity.ok().body(list);
    }
}
