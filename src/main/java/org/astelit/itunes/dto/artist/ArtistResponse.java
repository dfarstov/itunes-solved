package org.astelit.itunes.dto.artist;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.dto.EntityResponse;
import org.astelit.itunes.entity.Artist;
import org.astelit.itunes.entity.User;

@Getter
@Setter
public class ArtistResponse extends EntityResponse {
    private final String name;

    public ArtistResponse(Artist artist) {
        super(artist);
        this.name = artist.getName();
    }
}
