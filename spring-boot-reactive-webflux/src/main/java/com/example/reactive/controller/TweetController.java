package com.example.reactive.controller;

import com.example.reactive.exception.TweetNotFoundException;
import com.example.reactive.model.Tweet;
import com.example.reactive.payload.ErrorResponse;
import com.example.reactive.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @GetMapping("/tweets")
    public Flux<List<Tweet>> getAllTweets() {
        return tweetService.findAll();
    }

    @PostMapping("/tweets")
    public Mono<Tweet> createTweets(@Valid @RequestBody Tweet tweet) {
        return tweetService.save(tweet);
    }

    @GetMapping("/tweets/{id}")
    public Mono<ResponseEntity<Tweet>> getTweetById(@PathVariable(value = "id") String tweetId) {
        return tweetService.findById(tweetId)
                .map(savedTweet -> ResponseEntity.ok(savedTweet))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/tweets/{id}")
    public Mono<ResponseEntity<Tweet>> updateTweet(@PathVariable(value = "id") String tweetId,
                                                   @Valid @RequestBody Tweet tweet) {
        return tweetService.findById(tweetId)
                .flatMap(existingTweet -> {
                    existingTweet.setText(tweet.getText());
                    return tweetService.save(existingTweet);
                })
                .map(updateTweet -> new ResponseEntity<>(updateTweet, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/tweets/{id}")
    public Mono<ResponseEntity<Void>> deleteTweet(@PathVariable(value = "id") String tweetId) {

        return tweetService.findById(tweetId)
                .flatMap(existingTweet ->
                        tweetService.delete(existingTweet)
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Tweets are Sent to the client as Server Sent Events
    @GetMapping(value = "/stream/tweets", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<List<Tweet>> streamAllTweets() {
        return tweetService.findAll();
    }




    /*
        Exception Handling Examples (These can be put into a @ControllerAdvice to handle exceptions globally)
    */

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity handleDuplicateKeyException(DuplicateKeyException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("A Tweet with the same text already exists"));
    }

    @ExceptionHandler(TweetNotFoundException.class)
    public ResponseEntity handleTweetNotFoundException(TweetNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

}
