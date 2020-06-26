package org.astelit.itunes.repository;

import org.astelit.itunes.dto.album.AlbumSearchRequest;
import org.astelit.itunes.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long>, JpaSpecificationExecutor<Album> {

    default Page<Album> search(AlbumSearchRequest request) {
        return findAll((Specification<Album>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getArtist() != null)
                predicates.add(cb.equal(root.get("artist"), request.getArtist()));

            if (request.getName() != null)
                predicates.add(cb.equal(root.get("name"), request.getName()));

            if (request.getGenre() != null)
                predicates.add(cb.equal(root.get("genre"), request.getGenre()));

            query.orderBy(cb.desc(root.get("updatedAt")));

            return cb.and(predicates.toArray(new Predicate[0]));
        }, request.pageable());
    }
}
