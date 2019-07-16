package com.example.reactive.service;

import com.example.reactive.model.Tweet;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class TweetServiceImpl implements TweetService {

    List<Tweet> tweets= new ArrayList<>();
    Tweet tweet1 = new Tweet("CC111","tom",null);
    Tweet tweet2 = new Tweet("CC111","tom2",null);

    public TweetServiceImpl(){
        tweets.add(tweet1);
        tweets.add(tweet2);
    }

    @Override
    public Mono<Tweet> findById(String tweetId) {
        return null;
    }

    @Override
    public Flux<List<Tweet>> findAll() {
        return null;
    }

    @Override
    public Mono<Tweet> save(Tweet tweet) {
        return null;
    }

    @Override
    public Mono<Tweet> delete(Tweet tweet) {
        return null;
    }
}
