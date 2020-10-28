package com.levik.shorturl.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ShortUrlDomainTest {

    private static final String SEPARATOR = ",";

    private ShortUrlDomain testInstance;

    @BeforeEach
    void setUp() {
        testInstance = new ShortUrlDomain();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0, a", "1, b", "10, k", "12, m","14, o", "999, qh"})
    void shouldGenerateAndCheckShortUrl(String data) {
        //given
        String[] split = data.split(SEPARATOR);
        int identity = Integer.parseInt(split[0]);
        String expectedShortUrl = split[1].trim();

        //when
        String shortUrl = testInstance.identityToShortUrl(identity);
        long shortURLtoIdentity = testInstance.shortURLtoIdentity(shortUrl);

        //then
        assertThat(shortUrl).isEqualTo(expectedShortUrl);
        assertThat(shortURLtoIdentity).isEqualTo(shortURLtoIdentity);
    }

}