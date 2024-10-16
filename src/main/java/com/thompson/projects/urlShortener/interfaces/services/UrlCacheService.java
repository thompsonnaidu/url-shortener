package com.thompson.projects.urlShortener.interfaces.services;

public interface UrlCacheService {

    void cacheUrl(String originalUrl, String shortenUrl);
    String fetchOriginalUrl(String shortenUrl);

    String fetchShortenUrl(String originalUrl);
}
