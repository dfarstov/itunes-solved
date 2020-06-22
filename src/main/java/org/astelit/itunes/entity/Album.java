package org.astelit.itunes.entity;

import lombok.Getter;
import lombok.Setter;
import org.astelit.itunes.contstraint.AlbumName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
@Getter
@Setter
public class Album extends BaseEntity {
    @AlbumName
    private String name;

    @Column(columnDefinition = "DATE")
    private Date releaseDate;

    private String genre;

    @NotNull
    @JoinColumn(name = "id_artist")
    @ManyToOne(fetch = FetchType.EAGER)
    private Artist artist;

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
    private Set<Song> songs = new HashSet<>();
}
