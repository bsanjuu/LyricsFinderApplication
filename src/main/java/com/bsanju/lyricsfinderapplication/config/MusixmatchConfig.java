package com.bsanju.lyricsfinderapplication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MusixmatchConfig {

    @Value("${MUSIXMATCH_API_KEY}")
    private String apiKey;

    @Value("${MUSIXMATCH_API_URL}")
    private String apiUrl;

    public String getApiKey() {
        return apiKey;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
