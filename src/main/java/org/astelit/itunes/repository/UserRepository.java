package org.astelit.itunes.repository;

import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLogin(String login);

    boolean existsByLoginAndIdNot(String login, Long id);

    Page<User> findByLoginIsLikeOrderByLoginAsc(String login, Pageable pageable);
}
