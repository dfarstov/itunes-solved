package org.astelit.itunes.dto.album;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.contstraint.AlbumName;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.entity.Artist;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class AlbumSearchRequest extends SearchRequest {
    @AlbumName
    private String name;

    @NotNull
    private Long artist;

    @NotNull
    private Date releaseDate;

    @NotNull
    private String genre;
}
