package com.levik.shorturl.infa.repository.mapper;

import com.levik.shorturl.infa.api.dto.LongUrlResponse;
import com.levik.shorturl.infa.api.dto.ShortUrlRequest;
import com.levik.shorturl.infa.api.dto.ShortUrlResponse;
import com.levik.shorturl.infa.repository.entity.ShortUrlEntity;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlMapper {
    private static final int PLUS_ONE_YEAR = 1;

    public ShortUrlEntity toEntity(ShortUrlRequest request) {
        ShortUrlEntity shortUrlEntity = new ShortUrlEntity();
        shortUrlEntity.updateExpiredAtTo(PLUS_ONE_YEAR);
        shortUrlEntity.setLongUrl(request.getLongUrl());
        return shortUrlEntity;
    }

    public ShortUrlResponse toShortUrlResponse(String id) {
        ShortUrlResponse shortUrlResponse = new ShortUrlResponse();
        shortUrlResponse.setId(id);
        return shortUrlResponse;
    }

    public LongUrlResponse toLongUrlResponse(String longUrl) {
        LongUrlResponse longUrlResponse = new LongUrlResponse();
        longUrlResponse.setLongUrl(longUrl);

        return longUrlResponse;
    }
}
