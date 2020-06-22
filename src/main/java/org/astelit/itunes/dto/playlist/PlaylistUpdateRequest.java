package org.astelit.itunes.dto.playlist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraint.PlaylistName;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PlaylistUpdateRequest {
    @NotNull
    private Long id;

    @PlaylistName
    private String name;

    // TODO: tracks
}
