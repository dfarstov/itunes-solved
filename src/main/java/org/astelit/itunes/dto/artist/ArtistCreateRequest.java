package org.astelit.itunes.dto.artist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraint.Cyrillic;
import org.astelit.itunes.contstraint.Login;

@Getter
@Setter
@NoArgsConstructor
public class ArtistCreateRequest {
    @Cyrillic
    private String name;
}
