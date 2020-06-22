package org.astelit.itunes.dto.playlist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraint.PlaylistName;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PlaylistCreateRequest {
    @PlaylistName
    private String name;

    @NotNull
    private Long author;

    // TODO: tracks
}
