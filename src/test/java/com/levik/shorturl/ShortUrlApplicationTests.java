package com.levik.shorturl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = ShortUrlApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShortUrlApplicationTests {

	private static final String HOST = "http://localhost:%d%s";

	@LocalServerPort
	private int port;

	private TestRestTemplate restTemplate;

	@BeforeEach
	void setUp() {
		restTemplate = new TestRestTemplate();
	}

	@Test
	void shouldGetActuatorInfo() {
		//given
		String url = getHost("/actuator/info");

		//when
		ResponseEntity<Object> forEntity = restTemplate.getForEntity(url, Object.class);

		//then
		assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void shouldReturnIndex() {
		//given
		String url = getHost("/");
		String title = "TinyUrl Service";

		//when
		ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);

		//then
		assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(forEntity.getBody()).isNotNull();
		boolean isTitlePresent = forEntity.getBody().contains(title);
		assertThat(isTitlePresent).isTrue();
	}

	public String getHost(String path) {
		return String.format(HOST, port, path);
	}
}
