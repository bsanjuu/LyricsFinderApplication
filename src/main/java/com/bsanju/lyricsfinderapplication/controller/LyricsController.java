package com.bsanju.lyricsfinderapplication.controller;

import com.bsanju.lyricsfinderapplication.service.LyricsService;
import com.bsanju.lyricsfinderapplication.exception.LyricsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LyricsController {

    @Autowired
    private LyricsService lyricsService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/search")
    public String searchLyrics(
            @RequestParam(name = "songName") String songName,
            @RequestParam(name = "artistName") String artistName,
            Model model) {
        try {
            String lyrics = lyricsService.getLyrics(songName, artistName);
            model.addAttribute("lyrics", lyrics);
            model.addAttribute("songName", songName);
            model.addAttribute("artistName", artistName);
        } catch (LyricsNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "search-result";
    }
}
