package com.levik.shorturl.infa.repository;

import com.levik.shorturl.infa.repository.entity.ShortUrlEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlRepository extends CrudRepository<ShortUrlEntity, Long> {
}
