package dev.fabiosimones.workshopmongo.models.embedded;

import dev.fabiosimones.workshopmongo.models.entities.User;
import org.springframework.data.annotation.Id;

public class Author {

    private String id;
    private String name;

    public Author(){}

    public Author(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(User entity) {
        id = entity.getId();
        name = entity.getEmail();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
