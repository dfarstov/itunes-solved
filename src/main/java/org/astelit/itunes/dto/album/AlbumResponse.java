package org.astelit.itunes.dto.album;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.contstraint.AlbumName;
import org.astelit.itunes.dto.EntityResponse;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.entity.Artist;
import org.astelit.itunes.entity.Playlist;
import org.astelit.itunes.entity.Song;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class AlbumResponse extends EntityResponse {
    private String name;

    private String genre;

    private Date releaseDate;

    private String artist;

    // TODO: tracks

    public AlbumResponse(Album album) {
        super(album);
        this.name = album.getName();
        this.genre = album.getGenre();
        this.releaseDate = album.getReleaseDate();
        this.artist = album.getArtist().getName();
    }
}
