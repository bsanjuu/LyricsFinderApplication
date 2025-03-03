package com.bsanju.lyricsfinderapplication.exception;

public class LyricsNotFoundException extends RuntimeException {
    public LyricsNotFoundException(String message) {
        super(message);
    }
}