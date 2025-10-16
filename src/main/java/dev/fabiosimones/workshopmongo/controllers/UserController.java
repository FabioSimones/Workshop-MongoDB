package dev.fabiosimones.workshopmongo.controllers;

import dev.fabiosimones.workshopmongo.models.dto.PostDTO;
import dev.fabiosimones.workshopmongo.models.dto.UserDTO;
import dev.fabiosimones.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        UserDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO userDTO){
        userDTO = service.insert(userDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(userDTO);
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable String id,
                                          @RequestBody UserDTO userDTO){
        userDTO = service.update(id, userDTO);
        return ResponseEntity.ok().body(userDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<PostDTO>> getUserPosts(@PathVariable String id){
        List<PostDTO> list = service.getUserPosts(id);
        return ResponseEntity.ok().body(list);
    }
}
