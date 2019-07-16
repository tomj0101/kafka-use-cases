package com.example.reactive;

import com.example.reactive.model.Tweet;
import com.example.reactive.service.TweetService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReactiveApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
    TweetService tweetService;

	@Test
	public void testCreateTweet() {
		Tweet tweet = new Tweet("This is a Test Tweet");

		webTestClient.post().uri("/tweets")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(tweet), Tweet.class)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
				.expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.text").isEqualTo("This is a Test Tweet");
	}

	@Test
    public void testGetAllTweets() {
	    webTestClient.get().uri("/tweets")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Tweet.class);
    }

    @Test
    public void testGetSingleTweet() {
        Tweet tweet = tweetService.save(new Tweet("Other Tweet!")).block();

        webTestClient.get()
                .uri("/tweets/{id}", Collections.singletonMap("id", tweet.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response ->
                        Assertions.assertThat(response.getResponseBody()).isNotNull());
    }

    @Test
    public void testUpdateTweet() {
        Tweet tweet = tweetService.save(new Tweet("Initial Tweet")).block();

        Tweet newTweetData = new Tweet("Updated Tweet");

        webTestClient.put()
                .uri("/tweets/{id}", Collections.singletonMap("id", tweet.getId()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(newTweetData), Tweet.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.text").isEqualTo("Updated Tweet");
    }

    @Test
    public void testDeleteTweet() {
	    Tweet tweet = tweetService.save(new Tweet("To be deleted")).block();

	    webTestClient.delete()
                .uri("/tweets/{id}", Collections.singletonMap("id",  tweet.getId()))
                .exchange()
                .expectStatus().isOk();
    }
}
