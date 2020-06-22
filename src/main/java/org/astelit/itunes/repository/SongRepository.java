package org.astelit.itunes.repository;

import org.astelit.itunes.dto.album.AlbumSearchRequest;
import org.astelit.itunes.dto.song.SongSearchRequest;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long>, JpaSpecificationExecutor<Song> {

    default Page<Song> search(SongSearchRequest request) {
        return findAll((Specification<Song>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getAlbum() != null)
                predicates.add(cb.equal(root.get("album").get("id"), request.getAlbum()));

            predicates.add(cb.like(cb.upper(root.get("name")), request.getLikeString()));
            query.orderBy(cb.desc(root.get("updatedAt")));

            return cb.and(predicates.toArray(new Predicate[0]));
        }, request.pageable());
    }

    //NAME
    default Page<Song> findByName(SongSearchRequest request) {
        return findAll((Specification<Song>) (root, query, cb) -> {
            CriteriaQuery<Song> cq = cb.createQuery(Song.class);

            Predicate predicate = cb.equal(root.get("name"), request.getName());
            cq.where(predicate);

            return cb.and(predicate);
        }, request.pageable());
    }

    //ALBUM
    default Page<Song> findByAlbum(SongSearchRequest request) {
        return findAll((Specification<Song>) (root, query, cb) -> {
            CriteriaQuery<Song> cq = cb.createQuery(Song.class);

            Predicate predicate = cb.equal(root.get("album").get("name"), request.getAlbum());
            cq.where(predicate);

            return cb.and(predicate);
        }, request.pageable());
    }

    //ARTIST
    default Page<Song> findByArtist(SongSearchRequest request) {
        return findAll((Specification<Song>) (root, query, cb) -> {
            CriteriaQuery<Song> cq = cb.createQuery(Song.class);

            Predicate predicate = cb.equal(root.get("album").get("artist").get("name"), request.getArtist());
            cq.where(predicate);

            return cb.and(predicate);
        }, request.pageable());
    }

    //GENRE
    default Page<Song> findByGenre(SongSearchRequest request) {
        return findAll((Specification<Song>) (root, query, cb) -> {
            CriteriaQuery<Song> cq = cb.createQuery(Song.class);

            Predicate predicate = cb.equal(root.get("album").get("genre"), request.getGenre());
            cq.where(predicate);

            return cb.and(predicate);
        }, request.pageable());
    }
}
