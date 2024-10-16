package com.thompson.projects.urlShortener.models.requests;

public class ShortenerRequest {

    private String originalUrl;

    public String getOriginalUrl(){
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl){
        this.originalUrl= originalUrl;
    }
}

