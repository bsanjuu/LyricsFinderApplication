package com.bsanju.lyricsfinderapplication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MusixmatchConfig {

    @Value("${musixmatch.api.key}")
    private String apiKey;

    @Value("${musixmatch.api.url}")
    private String apiUrl;

    public String getApiKey() {
        return apiKey;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
