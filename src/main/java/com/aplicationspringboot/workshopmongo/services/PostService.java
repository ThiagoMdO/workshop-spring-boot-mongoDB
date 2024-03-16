package com.aplicationspringboot.workshopmongo.services;

import com.aplicationspringboot.workshopmongo.domain.Post;
import com.aplicationspringboot.workshopmongo.dto.PostDTO;
import com.aplicationspringboot.workshopmongo.repository.PostRepository;
import com.aplicationspringboot.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostDTO findPost(String id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Post not found"));
        return new PostDTO(post);
    }

    public List<PostDTO> findPostsByTitle(String title){
        List<Post> posts = postRepository.findByTitleContainingIgnoreCase(title);
        List<PostDTO> postDTOS = posts.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
        return postDTOS;
    }
}
