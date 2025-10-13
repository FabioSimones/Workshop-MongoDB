package dev.fabiosimones.workshopmongo.config;

import dev.fabiosimones.workshopmongo.models.embedded.Author;
import dev.fabiosimones.workshopmongo.models.embedded.Comment;
import dev.fabiosimones.workshopmongo.models.entities.Post;
import dev.fabiosimones.workshopmongo.models.entities.User;
import dev.fabiosimones.workshopmongo.repositories.PostRepository;
import dev.fabiosimones.workshopmongo.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @PostConstruct
    public void init(){
        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Grey", "alex@gmail.com");
        User bob = new User(null, "Bob White", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, Instant.parse("2021-02-13T11:15:01Z"), "Partiu viagem!", "Vou viajar para São Paulo.", new Author(bob));
        Post post2 = new Post(null, Instant.parse("2021-02-15T11:15:01Z"), "Bom dia!", "Acordei feliz hoje.", new Author(maria));

        Comment comment1 = new Comment("Boa viagem mano.", Instant.parse("2021-02-15T11:15:01Z"), new Author(alex));
        Comment comment2 = new Comment("Aproveite.", Instant.parse("2021-02-15T13:15:01Z"), new Author(maria));
        Comment comment3 = new Comment("Tenha um ótimo dia.", Instant.parse("2021-02-15T16:15:01Z"), new Author(alex));

        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post2.getComments().addAll(Arrays.asList(comment3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));

        userRepository.save(maria);
    }
}
