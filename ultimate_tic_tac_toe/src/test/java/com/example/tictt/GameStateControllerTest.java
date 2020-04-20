package com.example.tictt;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.tictt.dto.GameHistDto;
import com.example.tictt.dto.PlayerDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameStateControllerTest {

	@LocalServerPort
	private int port;
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	@Test
	public void testStartGame_NextMove() {
		// given
		HttpEntity<HttpStatus> entity = new HttpEntity<HttpStatus>(null, headers);
		restTemplate.execute(createURLWithPort("/api/gamestate/play"), HttpMethod.GET, null, null);
		restTemplate.execute(createURLWithPort("/api/gamestate/play"), HttpMethod.GET, null, null);
		// when
		ResponseEntity<HttpStatus> response = restTemplate.exchange(
				createURLWithPort("/api/gamestate/next?row=8&col=7&which=0"), HttpMethod.POST, entity,
				HttpStatus.class);
		ResponseEntity<HttpStatus> response2 = restTemplate.exchange(
				createURLWithPort("/api/gamestate/next?row=8&col=7&which=1"), HttpMethod.POST, entity,
				HttpStatus.class);
		ResponseEntity<HttpStatus> response3 = restTemplate.exchange(
				createURLWithPort("/api/gamestate/next?row=8&col=7&which=1"), HttpMethod.POST, entity,
				HttpStatus.class);
		// then
		assertThat(response.getBody(), is(HttpStatus.OK));
		assertThat(response2.getBody(), is(HttpStatus.OK));
		assertThat(response3.getBody(), is(HttpStatus.BAD_REQUEST));
	}

	@Test
	public void testCheckPlayerHist() {
		// given
		HttpEntity<GameHistDto> entity = new HttpEntity<GameHistDto>(null, headers);
		restTemplate.execute(createURLWithPort("/api/gamestate/play"), HttpMethod.GET, null, null);
		// when
		restTemplate.execute(createURLWithPort("/api/gamestate/next?row=1&col=1&which=0"), HttpMethod.POST, null, null);
		restTemplate.execute(createURLWithPort("/api/gamestate/next?row=2&col=1&which=0"), HttpMethod.POST, null, null);
		restTemplate.execute(createURLWithPort("/api/gamestate/next?row=1&col=2&which=0"), HttpMethod.POST, null, null);
		restTemplate.execute(createURLWithPort("/api/gamestate/next?row=2&col=2&which=0"), HttpMethod.POST, null, null);
		restTemplate.execute(createURLWithPort("/api/gamestate/next?row=1&col=3&which=0"), HttpMethod.POST, null, null);
		restTemplate.execute(createURLWithPort("/api/gamestate/next?row=2&col=3&which=0"), HttpMethod.POST, null, null);
		restTemplate.execute(createURLWithPort("/api/gamestate/next?row=1&col=4&which=0"), HttpMethod.POST, null, null);
		restTemplate.execute(createURLWithPort("/api/gamestate/next?row=2&col=4&which=0"), HttpMethod.POST, null, null);
		restTemplate.execute(createURLWithPort("/api/gamestate/next?row=1&col=5&which=0"), HttpMethod.POST, null, null);
		restTemplate.execute(createURLWithPort("/api/gamestate/check?which=0"), HttpMethod.GET, null, null);
		// then
		ResponseEntity<GameHistDto> response = restTemplate.exchange(
				createURLWithPort("/api/gamestate/checkplayerhist?index=0&which=0"), HttpMethod.GET, entity,
				GameHistDto.class);
		assertTrue(response.getBody() instanceof GameHistDto);
		assertTrue(response.getBody().wins != response.getBody().loses);
	}

	@Test
	public void testGetPlayerById() {
		// given
		HttpEntity<PlayerDto> entity = new HttpEntity<PlayerDto>(null, headers);
		restTemplate.execute(createURLWithPort("/api/gamestate/play"), HttpMethod.GET, null, null);
		// when
		ResponseEntity<PlayerDto> response = restTemplate.exchange(
				createURLWithPort("/api/gamestate/getplayer?index=0&which=0"), HttpMethod.GET, entity, PlayerDto.class);
		ResponseEntity<PlayerDto> response2 = restTemplate.exchange(
				createURLWithPort("/api/gamestate/getplayer?index=9&which=0"), HttpMethod.GET, entity, PlayerDto.class);
		// then
		assertThat(response.getBody().figure, anyOf(is(false), is(true)));
		assertThat(response2.getBody().figure, is(nullValue()));
	}

	@Test
	public void testCheckWinner() {
		// given
		HttpEntity<PlayerDto> entity = new HttpEntity<PlayerDto>(null, headers);
		restTemplate.execute(createURLWithPort("/api/gamestate/play"), HttpMethod.GET, null, null);
		restTemplate.execute(createURLWithPort("/api/gamestate/play"), HttpMethod.GET, null, null);
		// when
		ResponseEntity<PlayerDto> response = restTemplate.exchange(createURLWithPort("/api/gamestate/check?which=1"),
				HttpMethod.GET, entity, PlayerDto.class);
		// then
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.getBody().name, is(""));
	}

}
