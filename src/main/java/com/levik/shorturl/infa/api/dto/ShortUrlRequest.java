package com.levik.shorturl.infa.api.dto;

import com.levik.shorturl.infa.api.validation.UrlConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShortUrlRequest {
    @UrlConstraint
    private String longUrl;
}
