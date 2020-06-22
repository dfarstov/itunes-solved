package org.astelit.itunes.dto.artist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraint.Cyrillic;
import org.astelit.itunes.contstraint.Login;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ArtistUpdateRequest {
    @NotNull
    private Long id;

    private String name;
}
