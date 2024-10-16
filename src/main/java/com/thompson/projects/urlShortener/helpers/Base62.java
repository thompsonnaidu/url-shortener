package com.thompson.projects.urlShortener.helpers;

import org.springframework.stereotype.Component;

@Component
public class Base62 {
    private static final String BASE62_ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int BASE = 62;
    public static String encode(int number){
        StringBuilder encodedString= new StringBuilder();
        while(number >0){
            encodedString.append(BASE62_ALPHABET.charAt(number%BASE));
            number=number/BASE;
        }

        return encodedString.reverse().toString();
    }
}
