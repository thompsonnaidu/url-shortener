package com.thompson.projects.urlShortener.services;

import com.thompson.projects.urlShortener.helpers.Base62;
import com.thompson.projects.urlShortener.interfaces.services.CounterService;
import com.thompson.projects.urlShortener.interfaces.services.UrlCacheService;
import com.thompson.projects.urlShortener.interfaces.shortener.UrlShortenerService;
import com.thompson.projects.urlShortener.models.UrlMapping;
import com.thompson.projects.urlShortener.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

    @Autowired
    private CounterService counterService;

    @Autowired
    private UrlCacheService cacheService;

    @Autowired
    private UrlRepository urlRepository;

    private static final Logger logger = Logger.getLogger(UrlShortenerServiceImpl.class.getName());

    @Override
    public String shortenUrl(String originalUrl) {

        String cachedUrl= this.cacheService.fetchShortenUrl(originalUrl);
        if(cachedUrl !=null){
            return cachedUrl;
        }
        // generate Short url using counter based strategy
        Integer counter= counterService.getNextCounter();
        logger.fine(String.format("%d counter value while shortening url %s",counter,originalUrl));

        String shortUrl= Base62.encode(counter);

        UrlMapping urlMapping= new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);

        //store in the database
        urlRepository.save(urlMapping);

        //update the cache
        cacheService.cacheUrl(originalUrl,shortUrl);
        return shortUrl;
    }

    @Override
    public String getOriginalUrl(String shortenUrl) {

        String originalUrl=cacheService.fetchOriginalUrl(shortenUrl);

        if(originalUrl !=null){
            return originalUrl;
        }

        UrlMapping originalUrlInfo= urlRepository.findByShortUrl(shortenUrl);

        if(originalUrlInfo!=null){
            cacheService.cacheUrl(originalUrl,shortenUrl);
            return originalUrl;
        }
        // Todo throw an exception that the URL not found
        return null;

    }
}
