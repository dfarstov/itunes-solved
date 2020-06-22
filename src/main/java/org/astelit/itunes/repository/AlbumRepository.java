package org.astelit.itunes.repository;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import org.apache.commons.lang3.StringUtils;
import org.astelit.itunes.dto.album.AlbumResponse;
import org.astelit.itunes.dto.album.AlbumSearchRequest;
import org.astelit.itunes.dto.playlist.PlaylistSearchRequest;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.entity.Artist;
import org.astelit.itunes.entity.Playlist;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Persister;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long>, JpaSpecificationExecutor<Album> {

    default Page<Album> search(AlbumSearchRequest request) {
        return findAll((Specification<Album>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getArtist() != null)
                predicates.add(cb.equal(root.get("artist").get("id"), request.getArtist()));

            predicates.add(cb.like(cb.upper(root.get("name")), request.getLikeString()));
            query.orderBy(cb.desc(root.get("updatedAt")));

            return cb.and(predicates.toArray(new Predicate[0]));
        }, request.pageable());
    }

    //GET PAGES
    default Page<Album> getResult(AlbumSearchRequest request, String param, String requestData) {
        return findAll((Specification<Album>) (root, query, cb) -> cb.and(cb.equal(root.get(param), requestData)), request.pageable());
    }

    //NAME
    default Page<Album> findByName(AlbumSearchRequest request) {
        return getResult(request, "name", request.getName());
    }

    //GENRE
    default Page<Album> findByGenre(AlbumSearchRequest request) { return getResult(request, "genre", request.getGenre()); }

    //ARTIST
    default Page<Album> findByArtist(AlbumSearchRequest request) {
        return findAll((Specification<Album>) (root, query, cb) -> {
            Predicate predicate = cb.equal(root.get("artist"), request.getArtist());

            return cb.and(predicate);
        }, request.pageable());
    }
}
