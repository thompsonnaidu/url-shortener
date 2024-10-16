package com.thompson.projects.urlShortener.repositories;

import com.thompson.projects.urlShortener.models.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlMapping,Long> {
    UrlMapping findByShortUrl(String shortUrl);
}
