package com.yeahka.it.community.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    Iterable<Article> findAll(Sort sort);
    Page<Article> findAll(Pageable pageable);
}
