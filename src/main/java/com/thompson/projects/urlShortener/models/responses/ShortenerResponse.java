package com.thompson.projects.urlShortener.models.responses;

public class ShortenerResponse {

    private String originalUrl;
    private String shortUrl;

    public ShortenerResponse(){}

    public ShortenerResponse(String originalUrl,String shortUrl){
        this.originalUrl=originalUrl;
        this.shortUrl=shortUrl;
    }

    public String getOriginalUrl(){
        return this.originalUrl;
    }

    public String getShortUrl(){
        return this.shortUrl;
    }


    public void setOriginalUrl(String originalUrl){
        this.originalUrl=originalUrl;
    }

    public void setShortUrl(){
        this.shortUrl=shortUrl;
    }
}
