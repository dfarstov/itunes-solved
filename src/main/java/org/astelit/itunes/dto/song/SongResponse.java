package org.astelit.itunes.dto.song;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.dto.EntityResponse;
import org.astelit.itunes.entity.Playlist;
import org.astelit.itunes.entity.Song;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
public class SongResponse extends EntityResponse {
    private final String name;

    private final String album;

    @Temporal(TemporalType.TIME)
    private Date duration;

    private final String artist;

    // TODO: tracks

    public SongResponse(Song song) {
        super(song);
        this.name = song.getName();
        this.album = song.getAlbum().getName();
        this.duration = song.getDuration();
        this.artist = song.getAlbum().getArtist().getName();
    }
}
