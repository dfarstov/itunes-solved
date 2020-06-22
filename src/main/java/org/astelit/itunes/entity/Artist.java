package org.astelit.itunes.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "artists")
@Entity
@NoArgsConstructor
public class Artist extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY)
    private Set<Album> albums = new HashSet<>();
}
