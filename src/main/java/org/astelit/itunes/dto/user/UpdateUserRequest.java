package org.astelit.itunes.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraint.Cyrillic;
import org.astelit.itunes.contstraint.Login;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserRequest {
    @NotNull
    private Long id;

    @Login
    private String login;

    @Cyrillic
    private String name;
}
