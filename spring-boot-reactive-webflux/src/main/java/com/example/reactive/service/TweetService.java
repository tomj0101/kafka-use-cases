package com.example.reactive.service;

import com.example.reactive.model.Tweet;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TweetService {

    Mono<Tweet> findById(String tweetId);

    Flux<List<Tweet>> findAll();

    Mono<Tweet> save(Tweet tweet);

    Mono<Tweet> delete(Tweet tweet);


}
