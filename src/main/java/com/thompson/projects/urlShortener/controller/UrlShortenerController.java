package com.thompson.projects.urlShortener.controller;

import com.thompson.projects.urlShortener.interfaces.shortener.UrlShortenerService;
import com.thompson.projects.urlShortener.models.requests.ShortenerRequest;
import com.thompson.projects.urlShortener.models.responses.ShortenerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RestController("")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping
    private ResponseEntity<ShortenerResponse> shortenUrl(@RequestBody ShortenerRequest originalUrl){

        if(originalUrl == null || originalUrl.getOriginalUrl() == null){
            return ResponseEntity.badRequest().build();
        }

        String shortUrl=urlShortenerService.shortenUrl(originalUrl.getOriginalUrl());
        ShortenerResponse response= new ShortenerResponse(originalUrl.getOriginalUrl(),shortUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{shortUrl}")
    private ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortUrl){

        String originalUrl= urlShortenerService.getOriginalUrl(shortUrl);
        if(originalUrl ==null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
    }
}
