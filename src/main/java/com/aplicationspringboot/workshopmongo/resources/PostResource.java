package com.aplicationspringboot.workshopmongo.resources;

import com.aplicationspringboot.workshopmongo.dto.PostDTO;
import com.aplicationspringboot.workshopmongo.resources.util.URL;
import com.aplicationspringboot.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> findPostById(@PathVariable String id) {
        PostDTO postDTO = postService.findPost(id);
        return ResponseEntity.ok().body(postDTO);
    }

//    @GetMapping("/{title}")
//    public ResponseEntity<List<PostDTO>> findPostByTitle(@PathVariable("title") String title){
//        return ResponseEntity.ok().body(postService.findPostsByTitle(title));
//    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<PostDTO>> findPostByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<PostDTO> list = postService.findPostsByTitle(text);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/fullsearch")
    public ResponseEntity<List<PostDTO>> fullSearchAllFields(
    @RequestParam(value = "text", defaultValue = "") String text
    , @RequestParam(value = "minDate", defaultValue = "") String minDate
    , @RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());

        List<PostDTO> list = postService.fullSearch(text, min, max);
        return ResponseEntity.ok().body(list);
    }
}
