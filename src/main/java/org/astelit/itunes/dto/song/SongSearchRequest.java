package org.astelit.itunes.dto.song;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.dto.SearchRequest;

@Getter
@Setter
public class SongSearchRequest extends SearchRequest {
    private String name;
    private String album;
    private String artist;
    private String genre;
}
