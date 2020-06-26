package org.astelit.itunes.repository;

import org.astelit.itunes.dto.artist.ArtistSearchRequest;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long>, JpaSpecificationExecutor<Artist> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    default Page<Artist> search(ArtistSearchRequest request) {
        return findAll((Specification<Artist>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getName() != null)
                predicates.add(cb.equal(root.get("name"), request.getName()));

            if (request.getGenre() != null) {
                Join<Album, Artist> join = root.join("albums", JoinType.LEFT);
                predicates.add(cb.equal(join.get("genre"), request.getGenre()));
            }

            query.orderBy(cb.desc(root.get("updatedAt")));

            return cb.and(predicates.toArray(new Predicate[0]));
        }, request.pageable());
    }
}

