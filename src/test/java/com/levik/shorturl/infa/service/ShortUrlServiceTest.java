package com.levik.shorturl.infa.service;

import com.levik.shorturl.ShortUrlApplication;
import com.levik.shorturl.infa.api.dto.LongUrlResponse;
import com.levik.shorturl.infa.api.dto.ShortUrlRequest;
import com.levik.shorturl.infa.api.dto.ShortUrlResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = ShortUrlApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShortUrlServiceTest {

    @Autowired
    private ShortUrlService testInstance;

    @Test
    void shouldSubmitAndReturnLongUrlByIdentity() {
        //given
        String expectedLongUrl = "http://localhost:8080/test";
        ShortUrlRequest request = new ShortUrlRequest();
        request.setLongUrl(expectedLongUrl);

        //when
        ShortUrlResponse shortUrlResponse = testInstance.addLongUrl(request);
        assertThat(shortUrlResponse).isNotNull();
        LongUrlResponse longUrl = testInstance.getLongUrl(shortUrlResponse.getId());

        //then
        assertThat(longUrl.getLongUrl()).isEqualTo(expectedLongUrl);
    }


}