package com.bsanju.lyricsfinderapplication.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // ✅ Ignore unknown fields in full response
public class LyricsResponse {
    @JsonProperty("message")
    private Message message;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true) // ✅ Ignore unknown fields in Message object
    public static class Message {
        @JsonProperty("header")
        private Header header;

        @JsonProperty("body")
        private Body body;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true) // ✅ Ignore unknown fields in Header object
    public static class Header {
        @JsonProperty("status_code")
        private int statusCode;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true) // ✅ Ignore unknown fields in Body object
    public static class Body {
        @JsonProperty("lyrics")
        private Lyrics lyrics;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true) // ✅ Ignore unknown fields in Lyrics object
    public static class Lyrics {
        @JsonProperty("lyrics_body")
        private String lyricsBody;
    }
}
