package dev.fabiosimones.workshopmongo.services;

import dev.fabiosimones.workshopmongo.models.dto.UserDTO;
import dev.fabiosimones.workshopmongo.models.entities.User;
import dev.fabiosimones.workshopmongo.repositories.UserRepository;
import dev.fabiosimones.workshopmongo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDTO> findAll(){
        List<User> list = repository.findAll();
        return list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
    }

    public UserDTO findById(String id){
        User entity = getEntityById(id);
        return new UserDTO(entity);
    }

    public UserDTO insert(UserDTO userDTO){
        User entity = new User();
        copyDTOToEntity(userDTO, entity);
        entity = repository.insert(entity);

        return new UserDTO(entity);
    }

    public UserDTO update(String id, UserDTO userDTO){
        User entity = getEntityById(id);
        copyDTOToEntity(userDTO, entity);
        entity = repository.save(entity);

        return new UserDTO(entity);
    }

    private User getEntityById(String id){
        Optional<User> result = repository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado."));
    }

    private void copyDTOToEntity(UserDTO userDTO, User entity) {
        entity.setName(userDTO.getName());
        entity.setEmail(userDTO.getEmail());
    }
}
