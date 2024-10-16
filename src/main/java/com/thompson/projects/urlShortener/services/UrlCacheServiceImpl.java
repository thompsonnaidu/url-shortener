package com.thompson.projects.urlShortener.services;

import com.thompson.projects.urlShortener.interfaces.services.UrlCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UrlCacheServiceImpl implements UrlCacheService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private final String URL_PREFIX="url:";
    @Override
    public void cacheUrl(String originalUrl, String shortenUrl) {

        redisTemplate.opsForValue().set(URL_PREFIX+shortenUrl,originalUrl);
        redisTemplate.opsForValue().set(URL_PREFIX + originalUrl, shortenUrl);

    }

    @Override
    public String fetchOriginalUrl(String shortenUrl) {

       return redisTemplate.opsForValue().get(URL_PREFIX+shortenUrl);

    }

    @Override
    public String fetchShortenUrl(String originalUrl) {
        return redisTemplate.opsForValue().get(URL_PREFIX+originalUrl);
    }
}
