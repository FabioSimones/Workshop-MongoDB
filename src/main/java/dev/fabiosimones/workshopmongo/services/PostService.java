package dev.fabiosimones.workshopmongo.services;

import dev.fabiosimones.workshopmongo.models.dto.PostDTO;
import dev.fabiosimones.workshopmongo.models.dto.UserDTO;
import dev.fabiosimones.workshopmongo.models.entities.Post;
import dev.fabiosimones.workshopmongo.models.entities.User;
import dev.fabiosimones.workshopmongo.repositories.PostRepository;
import dev.fabiosimones.workshopmongo.repositories.UserRepository;
import dev.fabiosimones.workshopmongo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public PostDTO findById(String id){
        Post entity = getEntityById(id);
        return new PostDTO(entity);
    }

    private Post getEntityById(String id){
        Optional<Post> result = repository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado."));
    }

}
