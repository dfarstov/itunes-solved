package org.astelit.itunes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astelit.itunes.contstraint.Cyrillic;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

@Getter
@Setter
@Table(name = "songs")
@Entity
@NoArgsConstructor
public class  Song extends BaseEntity {
    private String name;

    @Temporal(TemporalType.TIME)
    private Date duration;

    @NotNull
    @JoinColumn(name = "id_album")
    @ManyToOne(fetch = FetchType.EAGER)
    private Album album;

    @ManyToMany(mappedBy = "songs", fetch = FetchType.EAGER)
    Set<Playlist> playlists = new HashSet<>();
}
