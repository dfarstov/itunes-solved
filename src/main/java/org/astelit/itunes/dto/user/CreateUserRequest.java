package org.astelit.itunes.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraint.Cyrillic;
import org.astelit.itunes.contstraint.Login;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequest {
    @Login
    private String login;

    @Cyrillic
    private String name;
}
