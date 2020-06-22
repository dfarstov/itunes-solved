package org.astelit.itunes.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.dto.EntityResponse;
import org.astelit.itunes.entity.User;

@Getter
@Setter
public class UserResponse extends EntityResponse {
    private final String login;
    private final String name;

    public UserResponse(User user) {
        super(user);
        this.login = user.getLogin();
        this.name = user.getName();
    }
}
