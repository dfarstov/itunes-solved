package org.astelit.itunes.dto.album;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraint.AlbumName;
import org.astelit.itunes.contstraint.PlaylistName;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AlbumUpdateRequest {
    @NotNull
    private Long id;

    @AlbumName
    private String name;

    @NotNull
    private Date releaseDate;

    @NotNull
    private String genre;
    // TODO: tracks
}
