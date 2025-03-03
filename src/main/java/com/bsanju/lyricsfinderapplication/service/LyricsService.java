package com.bsanju.lyricsfinderapplication.service;

import com.bsanju.lyricsfinderapplication.config.MusixmatchConfig;
import com.bsanju.lyricsfinderapplication.dto.LyricsResponse;
import com.bsanju.lyricsfinderapplication.exception.LyricsNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class LyricsService {

    @Autowired
    private WebClient webClient;

    @Autowired
    private MusixmatchConfig musixmatchConfig;

    public String getLyrics(String songName, String artistName) {
        // Build API URL
        String url = UriComponentsBuilder.fromHttpUrl(musixmatchConfig.getApiUrl() + "/matcher.lyrics.get")
                .queryParam("apikey", musixmatchConfig.getApiKey())
                .queryParam("q_track", songName)
                .queryParam("q_artist", artistName)
                .toUriString();

        log.info("API Request URL: {}", url);

        // Call API and log response as JSON
        String jsonResponse = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info("Raw API Response: {}", jsonResponse);

        try {
            // Convert JSON response to DTO
            ObjectMapper objectMapper = new ObjectMapper();
            LyricsResponse response = objectMapper.readValue(jsonResponse, LyricsResponse.class);

            log.info("Parsed API Response: {}", response);

            if (response == null || response.getMessage() == null || response.getMessage().getBody() == null
                    || response.getMessage().getBody().getLyrics() == null) {
                throw new LyricsNotFoundException("Lyrics not found in API response.");
            }

            String lyrics = response.getMessage().getBody().getLyrics().getLyricsBody();
            log.info("Fetched Lyrics: \n{}", lyrics);

            return lyrics.replace("\n", "<br>");

        } catch (Exception e) {
            log.error("Error processing API response: {}", e.getMessage());
            throw new LyricsNotFoundException("Error processing lyrics data.");
        }
    }


}
