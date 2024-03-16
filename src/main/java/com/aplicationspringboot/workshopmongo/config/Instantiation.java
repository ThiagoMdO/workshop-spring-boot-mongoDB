package com.aplicationspringboot.workshopmongo.config;

import com.aplicationspringboot.workshopmongo.domain.Post;
import com.aplicationspringboot.workshopmongo.domain.User;
import com.aplicationspringboot.workshopmongo.dto.AuthorDTO;
import com.aplicationspringboot.workshopmongo.dto.CommentDTO;
import com.aplicationspringboot.workshopmongo.repository.PostRepository;
import com.aplicationspringboot.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... arg0) throws Exception {

        SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
        fm.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post p1 = new Post(null, fm.parse("21/03/2018"), "Partiu viagem", "Vou viajar para Bahia", new AuthorDTO(maria));
        Post p2 = new Post(null, fm.parse("23/03/2018"), "Bom dia", "Estou correndo da Bahia", new AuthorDTO(maria));

        CommentDTO com1 = new CommentDTO("Boa viagem mano", fm.parse("21/03/2018"), new AuthorDTO(alex));
        CommentDTO com2 = new CommentDTO("Aproveite!", fm.parse("22/03/2018"), new AuthorDTO(bob));
        CommentDTO com3 = new CommentDTO("Tenha um ótimo dia!", fm.parse("23/03/2018"), new AuthorDTO(alex));

        p1.getComments().addAll(Arrays.asList(com1, com2));
        p2.getComments().add(com3);

        postRepository.saveAll(Arrays.asList(p1, p2));

        maria.getPosts().addAll(Arrays.asList(p1, p2));
        userRepository.save(maria);
    }
}
