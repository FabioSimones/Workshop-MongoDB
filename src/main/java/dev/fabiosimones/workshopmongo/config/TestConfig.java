package dev.fabiosimones.workshopmongo.config;

import dev.fabiosimones.workshopmongo.models.entities.User;
import dev.fabiosimones.workshopmongo.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init(){
        userRepository.deleteAll();
        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Grey", "alex@gmail.com");
        User bob = new User(null, "Bob White", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(maria, alex, bob));
    }
}
