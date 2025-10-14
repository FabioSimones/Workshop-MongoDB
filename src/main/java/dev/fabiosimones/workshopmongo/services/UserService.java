package dev.fabiosimones.workshopmongo.services;

import dev.fabiosimones.workshopmongo.models.dto.UserDTO;
import dev.fabiosimones.workshopmongo.models.entities.User;
import dev.fabiosimones.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDTO> findAll(){
        List<User> list = repository.findAll();
        return list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
    }
}
