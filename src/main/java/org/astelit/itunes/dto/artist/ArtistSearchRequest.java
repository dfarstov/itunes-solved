package org.astelit.itunes.dto.artist;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.contstraint.AlbumName;
import org.astelit.itunes.dto.SearchRequest;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class ArtistSearchRequest extends SearchRequest {
    private String name;
    private String genre;
}
