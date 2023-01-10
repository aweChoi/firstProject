package org.example.config.auth.dto;

import lombok.Getter;
import org.example.domain.user.User;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    public String name;
    public String email;
    public String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
