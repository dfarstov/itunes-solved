package org.astelit.itunes.dto.playlist;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.dto.SearchRequest;

@Getter
@Setter
public class PlaylistSearchRequest extends SearchRequest {
    private Long author;
    private String artist;
    private String album;
    private String song;
}
