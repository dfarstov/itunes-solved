package org.astelit.itunes.dto.song;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraint.PlaylistName;
import org.astelit.itunes.contstraint.SongDuration;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class SongCreateRequest {
    @PlaylistName
    private String name;

    @NotNull
    private Long album;

    @NotNull
    @Temporal(TemporalType.TIME)
    private Date duration;

    // TODO: tracks
}
