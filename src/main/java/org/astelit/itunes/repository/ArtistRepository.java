package org.astelit.itunes.repository;

import liquibase.pro.packaged.A;
import org.astelit.itunes.dto.album.AlbumSearchRequest;
import org.astelit.itunes.dto.artist.ArtistResponse;
import org.astelit.itunes.dto.artist.ArtistSearchRequest;
import org.astelit.itunes.dto.playlist.PlaylistSearchRequest;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.entity.Artist;
import org.hibernate.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long>, JpaSpecificationExecutor<Artist> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    Page<Artist> findByNameIsLikeOrderByNameAsc(String name, Pageable pageable);

    default Page<Artist> findByName(ArtistSearchRequest request) {
        return findAll((Specification<Artist>) (root, query, cb) -> {
            CriteriaQuery<Artist> cq = cb.createQuery(Artist.class);

            Predicate predicate = cb.equal(root.get("name"), request.getName());
            cq.where(predicate);

            return cb.and(predicate);
        }, request.pageable());
    }

    default Page<Artist> findByGenre(ArtistSearchRequest request) {
        return findAll((Specification<Artist>) (root, query, cb) -> {
            CriteriaQuery<Artist> cq = cb.createQuery(Artist.class);

            Join<Album, Artist> join = root.join("albums", JoinType.LEFT);

            Predicate predicate = cb.equal(join.get("genre"), request.getGenre());

            return cb.and(predicate);
        }, request.pageable());
    }
}

