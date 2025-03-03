package com.bsanju.lyricsfinderapplication.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MusixmatchConfig {
    private static final Dotenv dotenv = Dotenv.load(); // Load .env file

    public String getApiKey() {
        return dotenv.get("MUSIXMATCH_API_KEY"); // Read API key from .env
    }

    public String getApiUrl() {
        return dotenv.get("MUSIXMATCH_API_URL"); // Read API URL from .env
    }
}
