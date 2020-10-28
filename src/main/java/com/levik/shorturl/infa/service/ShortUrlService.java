package com.levik.shorturl.infa.service;

import com.levik.shorturl.domain.ShortUrlDomain;
import com.levik.shorturl.infa.api.dto.LongUrlResponse;
import com.levik.shorturl.infa.api.dto.ShortUrlRequest;
import com.levik.shorturl.infa.api.dto.ShortUrlResponse;
import com.levik.shorturl.infa.repository.ShortUrlRepository;
import com.levik.shorturl.infa.repository.exception.ShortUrlNotFoundException;
import com.levik.shorturl.infa.repository.mapper.ShortUrlMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ShortUrlService {
    public static final String CAN_FIND_LONG_URL_BY_ID = "Can't find long URL by id %s";

    private final ShortUrlDomain shortUrlDomain;
    private final ShortUrlMapper shortUrlMapper;
    private final ShortUrlRepository shortUrlRepository;

    @Transactional
    public ShortUrlResponse addLongUrl(ShortUrlRequest request) {
        var shortUrlEntity = shortUrlMapper.toEntity(request);
        var saveEntity = shortUrlRepository.save(shortUrlEntity);
        String shortIdentity = shortUrlDomain.identityToShortUrl(saveEntity.getId());
        return shortUrlMapper.toShortUrlResponse(shortIdentity);
    }

    public LongUrlResponse getLongUrl(String shortUrlId) {
        long identity = shortUrlDomain.shortURLtoIdentity(shortUrlId);
        var shortUrlEntity = shortUrlRepository.findById(identity)
                .orElseThrow(
                        () -> new ShortUrlNotFoundException(String.format(CAN_FIND_LONG_URL_BY_ID, shortUrlId))
                );
        return shortUrlMapper.toLongUrlResponse(shortUrlEntity.getLongUrl());
    }
}
