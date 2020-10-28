package com.levik.shorturl.infa.api;

import com.levik.shorturl.ShortUrlApplication;
import com.levik.shorturl.infa.api.dto.ShortUrlRequest;
import com.levik.shorturl.infa.api.dto.ShortUrlResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = ShortUrlApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShortUrlControllerTest {

    private static final String HOST = "http://localhost:%d%s";

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        restTemplate = new TestRestTemplate();
    }

    @Test
    void shouldSubmitLongUrlAndGetIdentity() {
        //given
        String host = getHost("/url");
        ShortUrlRequest request = new ShortUrlRequest();
        request.setLongUrl("http://localhost:8080/test");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ShortUrlRequest> requestHttpEntity = new HttpEntity<>(request, headers);

        //when
        ResponseEntity<ShortUrlResponse> responseEntity = restTemplate.postForEntity(host, requestHttpEntity, ShortUrlResponse.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getId()).isNotBlank();
    }

    @Test
    void shouldRedirectTo404PageInCaseNotFoundIdentity() {
        //given
        String id = UUID.randomUUID().toString();
        String host = getHost("/url/" + id);

        //when
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(host, String.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        boolean pageTitle = responseEntity.getBody().contains("404");
        boolean pageMessage = responseEntity.getBody().contains("The link you requested could not be found");

        assertThat(pageTitle).isTrue();
        assertThat(pageMessage).isTrue();
    }

    @Test
    void shouldReturn400InCaseWrongUrl() {
        //given
        String host = getHost("/url");
        ShortUrlRequest request = new ShortUrlRequest();
        request.setLongUrl("123");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ShortUrlRequest> requestHttpEntity = new HttpEntity<>(request, headers);

        //when
        ResponseEntity<ShortUrlResponse> responseEntity = restTemplate.postForEntity(host, requestHttpEntity, ShortUrlResponse.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    public String getHost(String path) {
        return String.format(HOST, port, path);
    }

}