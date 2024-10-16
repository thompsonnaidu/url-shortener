package com.thompson.projects.urlShortener.interfaces.shortener;

public interface UrlShortenerService {

    String shortenUrl(String originalUrl);
    String getOriginalUrl(String shortenUrl);
}
