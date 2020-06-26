package org.astelit.itunes.repository;

import org.astelit.itunes.dto.playlist.PlaylistSearchRequest;
import org.astelit.itunes.dto.song.SongSearchRequest;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.entity.Artist;
import org.astelit.itunes.entity.Playlist;
import org.astelit.itunes.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public interface  PlaylistRepository extends JpaRepository<Playlist, Long>, JpaSpecificationExecutor<Playlist> {

    default Page<Playlist> search(PlaylistSearchRequest request) {
        return findAll((Specification<Playlist>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getAuthor() != null)
                predicates.add(cb.equal(root.get("author").get("id"), request.getAuthor()));

            if (request.getArtist() != null)
                predicates.add(cb.equal(root.get("songs").get("album").get("artist"), request.getArtist()));

            if (request.getAlbum() != null)
                predicates.add(cb.equal(root.get("songs").get("album"), request.getAlbum()));

            if (request.getSong() != null)
                predicates.add(cb.equal(root.get("songs").get("name"), request.getSong()));


            query.orderBy(cb.desc(root.get("updatedAt")));

            return cb.and(predicates.toArray(new Predicate[0]));
        }, request.pageable());
    }
}
